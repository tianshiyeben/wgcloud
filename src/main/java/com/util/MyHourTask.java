package com.util;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.ApplicationContextHelper;
import com.entity.host.IntrusionInfo;
import com.service.log.LogInfoService;
import com.util.staticvar.BatchData;
import com.util.staticvar.StaticKeys;

import ch.ethz.ssh2.Connection;

/**
 *
 * @ClassName:MyHourTask.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: MyHourTask.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MyHourTask extends TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(MyHourTask.class);
	
    @Override
    public void run() {
    	System.out.println("hourTask"+new Date());
    	LogInfoService logInfoService = (LogInfoService)ApplicationContextHelper.getBean(LogInfoService.class);
    	try{
	    	//入侵检测
	    	intrusionCheck();
    	}catch(Exception e){
    		logger.error("----hourError：",e);
    		logInfoService.save("admin","","入侵检测信息异常："+e.toString(),StaticKeys.LOG_ERROR);
    	}
    }
    
    /**
     * 入侵检测
     * @throws IOException 
     */
    public void intrusionCheck() throws IOException{
    	//入侵检测
    	HostInfo hostInfo = null;
    	for(int i = 0 ;i < StaticKeys.hostInfoList.size()&&i <=StaticKeys.HOST_NUM; i++){
    		hostInfo = StaticKeys.hostInfoList.get(i);
    		if(StaticKeys.badHostName.contains(hostInfo.getHostname())){
    			continue;
    		}
			Connection conn = CtrCommond.getConn(hostInfo);
			if(conn==null){
				continue;
			}
			//检查系统内核模块
			String lsmod = CtrCommond.doCommond(conn, LinuxCmd.lsmod);
			if(StringUtils.isEmpty(lsmod)){
				lsmod="";
			}
			//检查passwd文件修改时间
			String passwdFile = CtrCommond.doCommond(conn, LinuxCmd.passwd_update_time);
			if(StringUtils.isEmpty(passwdFile)){
				passwdFile="";
			}
			//检查crontab计划任务
			String crontab = CtrCommond.doCommond(conn, LinuxCmd.crontab);
			if(StringUtils.isEmpty(crontab)){
				crontab="";
			}
			//检查promisc计划任务，正常网卡不该在promisc模式
			String promisc = CtrCommond.doCommond(conn, LinuxCmd.promisc);
			if(StringUtils.isEmpty(promisc)){
				promisc="";
			}
			//检查rpc服务
			String rpcinfo = CtrCommond.doCommond(conn, LinuxCmd.rpcinfo);
			if(StringUtils.isEmpty(rpcinfo)){
				rpcinfo="";
			}
			IntrusionInfo intrusionInfo = new com.entity.host.IntrusionInfo();
			intrusionInfo.setLsmod(lsmod.trim());
			intrusionInfo.setPasswdInfo(passwdFile.trim());
			intrusionInfo.setCrontab(crontab.trim());
			intrusionInfo.setPromisc(promisc.trim());
			intrusionInfo.setRpcinfo(rpcinfo.trim());
			intrusionInfo.setAccount(StaticKeys.ADMIN_ACCOUNT);
			intrusionInfo.setHostname(hostInfo.getHostname());
			intrusionInfo.setAccountId(StaticKeys.ADMIN_ACCOUNT);
			intrusionInfo.setCreateTime(DateUtil.getNowTime());
			BatchData.INTRUSION_INFO_LIST.add(intrusionInfo);
			
    	}
    }
}