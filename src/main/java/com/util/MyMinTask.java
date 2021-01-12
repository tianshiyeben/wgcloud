package com.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;

import com.common.ApplicationContextHelper;
import com.entity.app.AppInfo;
import com.entity.app.AppState;
import com.entity.host.MemState;
import com.service.app.AppInfoService;
import com.service.log.LogInfoService;
import com.util.linux.LinuxStrUtil;
import com.util.msg.WarnMailUtil;
import com.util.staticvar.BatchData;
import com.util.staticvar.StaticKeys;
/**
 *
 * @ClassName:MyMinTask.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: MyMinTask.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MyMinTask extends TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(MyMinTask.class);
	
    @Override
    public void run() {
    	runDetail();
    }
    
    private void runDetail(){
    	logger.info("mintask"+new Date());
        // TODO Auto-generated method stub
    	HostInfo hostInfo = null;
    	AppInfoService appInfoService = (AppInfoService)ApplicationContextHelper.getBean(AppInfoService.class);
    	LogInfoService logInfoService = (LogInfoService)ApplicationContextHelper.getBean(LogInfoService.class);
    	for(int i = 0 ;i < StaticKeys.hostInfoList.size()&&i <StaticKeys.HOST_NUM; i++){
    		hostInfo = StaticKeys.hostInfoList.get(i);
    		if(StaticKeys.badHostName.contains(hostInfo.getHostname())){
    			continue;
    		}
			Connection conn = CtrCommond.getConn(hostInfo);
			if(conn==null){
				continue;
			}
			try{
				Timestamp t = DateUtil.getNowTime();
				//获取内存使用情况
				String mem = CtrCommond.doCommond(conn, LinuxCmd.VIEW_MEM);
				MemState memState = LinuxStrUtil.setViewMem(mem,hostInfo.getHostname(),t);
				//判断发送内存告警邮件
				WarnMailUtil.sendWarnInfo(memState);
				//获取cpu使用情况
				String cpu = CtrCommond.doCommond(conn, LinuxCmd.VMSTAT);
				LinuxStrUtil.setCpuState(cpu,hostInfo.getHostname(),t);
				//获取磁盘IO使用情况
				String diskio = CtrCommond.doCommond(conn, LinuxCmd.DISK_IO);
				LinuxStrUtil.setDiskIo(diskio,hostInfo.getHostname(),t);
				//获取网络吞吐率
				String netIo = CtrCommond.doCommond(conn, LinuxCmd.SAR_DEV_1);
				LinuxStrUtil.setNetIo(netIo,hostInfo.getHostname(),t);
				//获取tcp状态
				String tcp = CtrCommond.doCommond(conn, LinuxCmd.SAR_TCP_1);
				LinuxStrUtil.setTcp(tcp,hostInfo.getHostname(),t);
				//获取系统负载情况
				String load = CtrCommond.doCommond(conn, LinuxCmd.UPTIME);
				LinuxStrUtil.setSysLoad(load,hostInfo.getHostname(),t);
				//加载监控应用进程信息
				Map<String, Object> params = new HashMap<String, Object>();
				List<AppInfo> appInfoList =  appInfoService.selectAllByParams(params);
				for(AppInfo s : appInfoList){
					String ip = s.getHostname();
					if(hostInfo.getHostname().equals(ip)){
						String port = s.getAppPid();
						String gg = CtrCommond.doCommond(conn, LinuxCmd.dd.replace("{pid}", port));
						String remoteStr = getGG(gg,port);
						String[] appStateStr = remoteStr.split(StaticKeys.SPLIT_KG);
						if(appStateStr.length>1){
							AppState appState = new AppState();
							appState.setCpuPer(appStateStr[0]);
							appState.setMemPer(appStateStr[1]);
							appState.setAppInfoId(s.getId());
							appState.setCreateTime(t);
							BatchData.APP_STATE_LIST.add(appState);
							//判断是否需要发送告警邮件
							WarnMailUtil.sendAppWarnInfo(appState,s);
						}
					}
				}
			}catch(Exception e){
				logger.error(hostInfo.getHostname()+"----5MinError：",e);
				logInfoService.save("admin",hostInfo.getHostname(),"获取系统状态信息异常："+e.toString(),StaticKeys.LOG_ERROR);
			}
			conn.close();
		}
    }
    /**
     * 对进行占用的CPU和mem字符串，进行预处理
     * @param gg
     * @return
     */
    private String getGG(String gg,String pid){
    	if(StringUtils.isEmpty(gg)){
			return "";
		}
    	gg = gg.trim();
		String[] rows = gg.split(StaticKeys.SPLIT_BR);
		String[] cols = null;
		String row = "";
		for(int i = 1 ;i < rows.length; i++){
			row =  FormatUtil.replaceKg(rows[i]);
			cols = row.split(StaticKeys.SPLIT_KG);
			if(pid.equals(cols[1])){
				return cols[2]+" "+cols[3];
			}
		}
		return "";
    }

}