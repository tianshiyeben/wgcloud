package com.wgcloud.task;


import com.wgcloud.entity.*;
import com.wgcloud.mapper.*;
import com.wgcloud.service.*;
import com.wgcloud.util.*;
import com.wgcloud.util.jdbc.ConnectionUtil;
import com.wgcloud.util.jdbc.RDSConnection;
import com.wgcloud.util.msg.WarnMailUtil;
import com.wgcloud.util.msg.WarnPools;
import com.wgcloud.util.staticvar.BatchData;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @ClassName:ScheduledTask.java
 * @version v2.1
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: ScheduledTask.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Component
public class ScheduledTask {

    private Logger logger  = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * 线程池
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 40, 2, TimeUnit.MINUTES, new LinkedBlockingDeque<>());


    @Autowired
    SystemInfoService systemInfoService;
    @Autowired
    DeskStateService deskStateService;
    @Autowired
    LogInfoService logInfoService;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    CpuStateService cpuStateService;
    @Autowired
    MemStateService memStateService;
    @Autowired
    NetIoStateService netIoStateService;
    @Autowired
    SysLoadStateService sysLoadStateService;
    @Autowired
    TcpStateService tcpStateService;
    @Autowired
    AppStateService appStateService;
    @Autowired
    MailSetService mailSetService;
    @Autowired
    IntrusionInfoService intrusionInfoService;
    @Autowired
    HostInfoService hostInfoService;
    @Autowired
    DbInfoService dbInfoService;
    @Autowired
    DbTableService dbTableService;
    @Autowired
    DbTableCountService dbTableCountService;
    @Autowired
    HeathMonitorService heathMonitorService;
    @Autowired
    private RestUtil restUtil;
    @Autowired
    ConnectionUtil connectionUtil;


    /**
     * 90秒后执行，之后每隔10分钟执行, 单位：ms。
     */
    @Scheduled(initialDelay = 90000L, fixedRate = 10 * 60 * 1000)
    public   void heathMonitorTask() {
        Map<String, Object> params = new HashMap<>();
        List<HeathMonitor> heathMonitors = new ArrayList<HeathMonitor>();
        Date date = DateUtil.getNowTime();
        String sql = "";
        Long tableCount = 0l;
        try {
            List<HeathMonitor>  heathMonitorAllList = heathMonitorService.selectAllByParams(params);
            if(heathMonitorAllList.size()>0){
                for(HeathMonitor h : heathMonitorAllList){
                    int status = 500;
                     status = restUtil.get(h.getHeathUrl());
                    h.setCreateTime(date);
                    h.setHeathStatus(status+"");
                    heathMonitors.add(h);
                    if(!"200".equals(h.getHeathStatus())) {
                        Runnable runnable = () -> {
                            WarnMailUtil.sendHeathInfo(h);
                        };
                        executor.execute(runnable);
                    }
                }
                heathMonitorService.updateRecord(heathMonitors);
            }
        } catch (Exception e) {
            logger.error("服务健康检测任务错误",e);
            logInfoService.save("服务健康检测错误",e.toString(),StaticKeys.LOG_ERROR);
        }
    }



    /**
     * 60秒后执行，之后每隔120分钟执行, 单位：ms。
     */
    @Scheduled(initialDelay = 60000L, fixedRate = 120 * 60 * 1000)
    public   void tableCountTask() {
        Map<String, Object> params = new HashMap<>();
        List<DbTableCount> dbTableCounts = new ArrayList<DbTableCount>();
        Date date = DateUtil.getNowTime();
        String sql = "";
        Long tableCount = 0l;
        try {
            List<DbInfo>  dbInfos = dbInfoService.selectAllByParams(params);
            if(dbInfos.size()>0){
                List<DbTable>  dbTables = dbTableService.selectAllByParams(params);
                if(dbTables.size()>0){
                    for(DbTable dbTable : dbTables){
                        sql = RDSConnection.query_table_count.replace("{tableName}",dbTable.getTableName())  + dbTable.getWhereVal();
                        tableCount = connectionUtil.queryTableCount(dbInfos.get(0),sql);
                        DbTableCount dbTableCount = new DbTableCount();
                        dbTableCount.setCreateTime(date);
                        dbTableCount.setDbTableId(dbTable.getId());
                        dbTableCount.setTableCount(tableCount);
                        dbTableCounts.add(dbTableCount);
                        dbTable.setDateStr(DateUtil.getDateTimeString(date));
                        dbTable.setTableCount(tableCount);
                        dbTableService.updateById(dbTable);
                    }
                }
                dbTableCountService.saveRecord(dbTableCounts);
            }
        } catch (Exception e) {
            logger.error("数据表监控任务错误",e);
            logInfoService.save("数据表监控任务错误",e.toString(),StaticKeys.LOG_ERROR);
        }
    }

    /**
     * 30秒后执行，之后每隔2分钟执行, 单位：ms。
     */
    @Scheduled(initialDelay = 30000L, fixedRate = 2 * 60 * 1000)
    public synchronized  void commitTask() {
        logger.info("批量提交监控数据任务开始----------"+DateUtil.getCurrentDateTime());
        try {
                if(BatchData.APP_STATE_LIST.size()>0){
                    List<AppState> APP_STATE_LIST = new ArrayList<AppState>();
                    APP_STATE_LIST.addAll(BatchData.APP_STATE_LIST);
                    BatchData.APP_STATE_LIST.clear();
                    appStateService.saveRecord(APP_STATE_LIST);
                }
                if(BatchData.CPU_STATE_LIST.size()>0){
                    List<CpuState> CPU_STATE_LIST  = new ArrayList<CpuState>();
                    CPU_STATE_LIST.addAll(BatchData.CPU_STATE_LIST);
                    BatchData.CPU_STATE_LIST.clear();
                    cpuStateService.saveRecord(CPU_STATE_LIST);
                }
                if(BatchData.MEM_STATE_LIST.size()>0){
                    List<MemState> MEM_STATE_LIST =  new ArrayList<MemState>();
                    MEM_STATE_LIST.addAll(BatchData.MEM_STATE_LIST);
                    BatchData.MEM_STATE_LIST.clear();
                    memStateService.saveRecord(MEM_STATE_LIST);
                }
               /* if(BatchData.NETIO_STATE_LIST.size()>0){
                    netIoStateService.saveRecord(BatchData.NETIO_STATE_LIST);
                }*/
                if(BatchData.SYSLOAD_STATE_LIST.size()>0){
                    List<SysLoadState> SYSLOAD_STATE_LIST =  new ArrayList<SysLoadState>();
                    SYSLOAD_STATE_LIST.addAll(BatchData.SYSLOAD_STATE_LIST);
                    BatchData.SYSLOAD_STATE_LIST.clear();
                    sysLoadStateService.saveRecord(SYSLOAD_STATE_LIST);
                }
                /*if(BatchData.TCP_STATE_LIST.size()>0){
                    tcpStateService.saveRecord(BatchData.TCP_STATE_LIST);
                }
                if(BatchData.INTRUSION_INFO_LIST.size()>0){
                    intrusionInfoService.saveRecord(BatchData.INTRUSION_INFO_LIST);
                }*/
                if(BatchData.LOG_INFO_LIST.size()>0){
                    List<LogInfo> LOG_INFO_LIST =  new ArrayList<LogInfo>();
                    LOG_INFO_LIST.addAll(BatchData.LOG_INFO_LIST);
                    BatchData.LOG_INFO_LIST.clear();
                    logInfoService.saveRecord(LOG_INFO_LIST);
                }
                if(BatchData.DESK_STATE_LIST.size()>0){
                   Map<String, Object> paramsDel = new HashMap<String,Object>();

                    List<DeskState> DESK_STATE_LIST  =  new ArrayList<DeskState>();
                    DESK_STATE_LIST.addAll(BatchData.DESK_STATE_LIST);
                    BatchData.DESK_STATE_LIST.clear();
                    List<String> hostnameList  =  new ArrayList<String>();
                    for(DeskState deskState : DESK_STATE_LIST){
                        if(!hostnameList.contains(deskState.getHostname())){
                            hostnameList.add(deskState.getHostname());
                        }
                    }
                    for(String hostname : hostnameList){
                        paramsDel.put("hostname",hostname);
                        deskStateService.deleteByAccHname(paramsDel);
                    }
                    deskStateService.saveRecord(DESK_STATE_LIST);
                }
                if(BatchData.SYSTEM_INFO_LIST.size()>0){
                    Map<String, Object> paramsDel = new HashMap<String,Object>();
                    List<SystemInfo> SYSTEM_INFO_LIST  =  new ArrayList<SystemInfo>();
                    SYSTEM_INFO_LIST.addAll(BatchData.SYSTEM_INFO_LIST);
                    BatchData.SYSTEM_INFO_LIST.clear();
                    List<SystemInfo>   updateList = new ArrayList<SystemInfo>();
                    List<SystemInfo>   insertList = new ArrayList<SystemInfo>();
                    List<SystemInfo>   savedList = systemInfoService.selectAllByParams(paramsDel);
                    for(SystemInfo systemInfo : SYSTEM_INFO_LIST){
                        for(SystemInfo systemInfoS : savedList){
                            if(systemInfoS.getHostname().equals(systemInfo.getHostname())){
                                systemInfo.setId(systemInfoS.getId());
                                updateList.add(systemInfo);
                            }
                        }
                    }
                    systemInfoService.updateRecord(updateList);

                    for(SystemInfo systemInfo : SYSTEM_INFO_LIST){
                        boolean issaved = false;
                        for(SystemInfo systemInfoS :savedList){
                            if(systemInfoS.getHostname().equals(systemInfo.getHostname())){
                                issaved = true;
                                break;
                            }
                        }
                        if(!issaved){
                            insertList.add(systemInfo);
                        }
                    }
                    systemInfoService.saveRecord(insertList);

                }
                if(BatchData.APP_INFO_LIST.size()>0){
                    Map<String, Object> paramsDel = new HashMap<String,Object>();
                    for(AppInfo appInfo : BatchData.APP_INFO_LIST){
                        paramsDel.put("hostname",appInfo.getHostname());
                        paramsDel.put("appPid",appInfo.getAppPid());
                        appInfoService.deleteByHostName(paramsDel);
                    }

                    List<AppInfo> APP_INFO_LIST  =  new ArrayList<AppInfo>();
                    APP_INFO_LIST.addAll(BatchData.APP_INFO_LIST);
                    BatchData.APP_INFO_LIST.clear();
                    appInfoService.saveRecord(APP_INFO_LIST);
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("批量提交监控数据错误----------",e);
            logInfoService.save("commitTask","批量提交监控数据错误："+e.toString(),StaticKeys.LOG_ERROR);
        }
        logger.info("批量提交监控数据任务结束----------"+DateUtil.getCurrentDateTime());
    }

    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    CpuStateMapper cpuStateMapper;
    @Autowired
    DeskStateMapper deskStateMapper;
    @Autowired
    MemStateMapper memStateMapper;
    @Autowired
    NetIoStateMapper netIoStateMapper;
    @Autowired
    SysLoadStateMapper sysLoadStateMapper;
    @Autowired
    TcpStateMapper tcpStateMapper;
    @Autowired
    AppInfoMapper appInfoMapper;
    @Autowired
    AppStateMapper appStateMapper;
    @Autowired
    MailSetMapper mailSetMapper;
    @Autowired
    IntrusionInfoMapper intrusionInfoMapper;
    @Autowired
    LogInfoMapper logInfoMapper;

    /**
     *每天凌晨1:10执行
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void clearHisdataTask() {
        logger.info("定时清空历史数据任务开始----------"+DateUtil.getCurrentDateTime());
        WarnPools.clearOldData();//清空发告警邮件的记录
        String nowTime = DateUtil.getCurrentDateTime();
        //30天前时间
        String thrityDayBefore = DateUtil.getDateBefore(nowTime, 30);
        String dayBefore_15 = DateUtil.getDateBefore(nowTime, 15);
        Map<String, Object> paramsDel = new HashMap<String,Object>();
        try {
            paramsDel.put(StaticKeys.SEARCH_END_TIME,thrityDayBefore);
            //执行删除操作begin
            if(paramsDel.get(StaticKeys.SEARCH_END_TIME)!=null&&!"".equals(paramsDel.get(StaticKeys.SEARCH_END_TIME))){
                cpuStateMapper.deleteByAccountAndDate(paramsDel);//删除cpu监控信息
                deskStateMapper.deleteByAccountAndDate(paramsDel);//删除磁盘监控信息
                memStateMapper.deleteByAccountAndDate(paramsDel);//删除内存监控信息
                netIoStateMapper.deleteByAccountAndDate(paramsDel);//删除吞吐率监控信息
                sysLoadStateMapper.deleteByAccountAndDate(paramsDel);//删除负载状态监控信息
                tcpStateMapper.deleteByAccountAndDate(paramsDel);//删除tcp监控信息
                appInfoMapper.deleteByDate(paramsDel);
                appStateMapper.deleteByDate(paramsDel);
                systemInfoMapper.deleteByAccountAndDate(paramsDel);
                intrusionInfoMapper.deleteByAccountAndDate(paramsDel);
                //删除30天前的日志信息
                logInfoMapper.deleteByDate(paramsDel);
                //删除15天前数据库表统计信息
                paramsDel.put(StaticKeys.SEARCH_END_TIME,dayBefore_15);
                dbTableCountService.deleteByDate(paramsDel);
            }
            //执行删除操作end



        } catch (Exception e) {
            logger.error("定时清空历史数据任务出错：",e);
            logInfoService.save("clearHisdataTask","定时清空历史数据错误："+e.toString(),StaticKeys.LOG_ERROR);
        }
        logger.info("定时清空历史数据任务结束----------"+DateUtil.getCurrentDateTime());
    }



}
