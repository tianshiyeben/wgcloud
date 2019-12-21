package com.wgcloud;


import cn.hutool.json.JSONObject;
import com.wgcloud.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @ClassName:ScheduledTask.java
 * @version V1.0
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: ScheduledTask.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Component
public class ScheduledTask {

    private Logger logger  = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    private RestUtil restUtil;
    @Autowired
    private CommonConfig commonConfig;

    private SystemInfo systemInfo = null;


    /**
     * 线程池
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 40, 2, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    /**
     * 60秒后执行，每隔5分钟执行, 单位：ms。
     */
    @Scheduled(initialDelay = 60 * 1000L, fixedRate = 5 * 60 * 1000)
    public void minTask() {
        JSONObject jsonObject = new JSONObject();
        LogInfo logInfo = new LogInfo();
        Timestamp t = FormatUtil.getNowTime();
        logInfo.setHostname(commonConfig.getBindIp()+"：Agent错误");
        logInfo.setCreateTime(t);
        try {
            // cpu信息
            CpuState cpuState = SigarUtil.cpu();
            cpuState.setCreateTime(t);
            // 内存信息
            MemState memState = SigarUtil.memory();
            memState.setCreateTime(t);
            // 系统负载信息
            SysLoadState sysLoadState = SigarUtil.getLoadState(systemInfo);
            if(sysLoadState!=null) {
                sysLoadState.setCreateTime(t);
            }
            if(cpuState!=null) {
                jsonObject.put("cpuState", cpuState);
            }
            if(memState!=null) {
                jsonObject.put("memState", memState);
            }
            if(sysLoadState!=null) {
                jsonObject.put("sysLoadState", sysLoadState);
            }
            //进程信息
            String pid = commonConfig.getAppId();
            if(!StringUtils.isEmpty(pid)){
                AppInfo appInfo = new AppInfo();
                appInfo.setHostname(commonConfig.getBindIp());
                appInfo.setAppPid(pid);
                appInfo.setCreateTime(t);
                AppState appState =  SigarUtil.getLoadPid(pid);
                appState.setCreateTime(t);
                if(appState!=null) {
                    appInfo.setMemPer(appState.getMemPer());
                    appInfo.setCpuPer(appState.getCpuPer());
                    jsonObject.put("appInfo", appInfo);
                    jsonObject.put("appState", appState);
                }
            }

        } catch (SigarException e) {
            e.printStackTrace();
            logInfo.setInfoContent(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logInfo.setInfoContent(e.toString());
        }finally {
            if(!StringUtils.isEmpty(logInfo.getInfoContent())){
                jsonObject.put("logInfo",logInfo);
            }
            restUtil.post(commonConfig.getServerUrl()+"/wgcloud/agent/minTask",jsonObject);
        }

    }

    /**
     * 10秒后执行，每隔24小时执行, 单位：ms。
     */
    @Scheduled(initialDelay = 10 * 1000L, fixedRate = 24 * 60 * 60 * 1000)
    public void dayTask() {
        JSONObject jsonObject = new JSONObject();
        Timestamp t = FormatUtil.getNowTime();
        LogInfo logInfo = new LogInfo();
        logInfo.setHostname(commonConfig.getBindIp()+"：Agent错误");
        logInfo.setCreateTime(t);
        try {
            systemInfo =  SigarUtil.os();
            systemInfo.setCreateTime(t);
            List<DeskState>  deskStateList =  SigarUtil.file(t);
            if(systemInfo!=null) {
                jsonObject.put("systemInfo", systemInfo);
            }
            if(deskStateList!=null) {
                jsonObject.put("deskStateList", deskStateList);
            }
        }  catch (SigarException e) {
            e.printStackTrace();
            logInfo.setInfoContent(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logInfo.setInfoContent(e.toString());
        }finally {
            if(!StringUtils.isEmpty(logInfo.getInfoContent())){
                jsonObject.put("logInfo",logInfo);
            }
            restUtil.post(commonConfig.getServerUrl()+"/wgcloud/agent/dayTask",jsonObject);
        }
    }


}
