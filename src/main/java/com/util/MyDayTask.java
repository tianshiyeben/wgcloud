package com.util;

import java.util.ArrayList;
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
import com.entity.host.DeskState;
import com.entity.host.SystemInfo;
import com.service.app.AppInfoService;
import com.service.host.DeskStateService;
import com.service.host.SystemInfoService;
import com.service.log.LogInfoService;
import com.util.linux.LinuxStrUtil;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:MyDayTask.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: MyDayTask.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MyDayTask extends TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(MyDayTask.class);
	
    @Override
    public void run() {
    	logger.info("dayTask"+new Date());
    	StaticKeys.badHostName.clear();
    	SystemInfoService systemInfoService = (SystemInfoService)ApplicationContextHelper.getBean(SystemInfoService.class);
    	DeskStateService deskStateService = (DeskStateService)ApplicationContextHelper.getBean(DeskStateService.class);
    	LogInfoService logInfoService = (LogInfoService)ApplicationContextHelper.getBean(LogInfoService.class);
    	 // TODO Auto-generated method stub
    	HostInfo hostInfo = null;
    	try{
	    	for(int i = 0 ;i < StaticKeys.hostInfoList.size()&&i <=StaticKeys.HOST_NUM; i++){
	    		hostInfo = StaticKeys.hostInfoList.get(i);
				Connection conn = CtrCommond.getConn(hostInfo);
				if(conn==null){
					SystemInfo systemInfo = new SystemInfo();
					systemInfo.setVersion(StaticKeys.CONN_HOST_ERROR);
					systemInfo.setVersionDetail("");
					systemInfo.setCpuNum("");
					systemInfo.setCpuCoreNum("");
					systemInfo.setYxDays("");
					systemInfo.setAccount(StaticKeys.ADMIN_ACCOUNT);
					systemInfo.setHostname(hostInfo.getHostname());
					systemInfo.setCpuXh("");
					systemInfo.setAccountId(StaticKeys.ADMIN_ACCOUNT);
					Map<String,Object> map= new HashMap<String,Object>();
					map.put("hostname", hostInfo.getHostname());
					map.put("account", StaticKeys.ADMIN_ACCOUNT);
					systemInfoService.deleteByAccHname(map);
					systemInfoService.save(systemInfo);
					StaticKeys.badHostName.add(hostInfo.getHostname());
					continue;
				}
				//加载系统信息
				String version = CtrCommond.doCommond(conn, LinuxCmd.SYSTEM_RELEASE);
				//加载系统详细信息
				String versionDetail = CtrCommond.doCommond(conn, LinuxCmd.UNAME_A);
				//加载物理cpu个数
				String cpuNum =  CtrCommond.doCommond(conn, LinuxCmd.WULI_CPU_NUM);
				//加载每个cpu的核数
				String cpuCoreNum =  CtrCommond.doCommond(conn, LinuxCmd.WULI_CPU_CORE_NUM);
				//加载系统已使用多少天
				String yxDays = FormatUtil.replaceKg(CtrCommond.doCommond(conn, LinuxCmd.UPTIME));
				//加载磁盘使用信息
				String df_hl = CtrCommond.doCommond(conn, LinuxCmd.DF_HL);
				//加载CPU型号
				String cpuXh = CtrCommond.doCommond(conn, LinuxCmd.CPU_XINGHAO);
				SystemInfo systemInfo = new SystemInfo();
				systemInfo.setVersion(version);
				systemInfo.setVersionDetail(versionDetail);
				systemInfo.setCpuNum(cpuNum);
				systemInfo.setCpuCoreNum(cpuCoreNum.substring(cpuCoreNum.length()-1));
				systemInfo.setYxDays(LinuxStrUtil.getYxDays(yxDays));
				systemInfo.setAccount(StaticKeys.ADMIN_ACCOUNT);
				systemInfo.setHostname(hostInfo.getHostname());
				systemInfo.setCpuXh(cpuXh.trim());
				systemInfo.setAccountId(StaticKeys.ADMIN_ACCOUNT);
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("hostname", hostInfo.getHostname());
				map.put("account", StaticKeys.ADMIN_ACCOUNT);
				systemInfoService.deleteByAccHname(map);
				systemInfoService.save(systemInfo);
				DeskState deskState = LinuxStrUtil.setDfHl(df_hl);
				if(null!=deskState){
					deskState.setAccount(StaticKeys.ADMIN_ACCOUNT);
					deskState.setAccountId(StaticKeys.ADMIN_ACCOUNT);
					deskState.setHostname(hostInfo.getHostname());
					deskStateService.deleteByAccHname(map);
					deskStateService.save(deskState);
				}
				conn.close();
	    }
    }catch(Exception e){
    	logger.error("----dayError：",e);
		logInfoService.save("admin",hostInfo.getHostname(),"获取系统信息异常："+e.toString(),StaticKeys.LOG_ERROR);
	} 
    }
}