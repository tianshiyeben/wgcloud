package com.util.msg;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.AppInit;
import com.common.ApplicationContextHelper;
import com.entity.app.AppInfo;
import com.entity.app.AppState;
import com.entity.host.MemState;
import com.service.msg.MsgInfoService;
import com.util.staticvar.HostKeys;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:WarnMailUtil.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: WarnMailUtil.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class WarnMailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WarnMailUtil.class);
	
	public static final String title_suffix = "【WGCLOUD】";
	
	public static final String wish_con = "，<a target='_blank' href='http://www.wgstart.com'>www.wgstart.com</a><p>祝生活愉快";
	
	public static final String content_suffix = "<p>WGCLOUD团队敬上";
	
	private static MsgInfoService msgInfoService = (MsgInfoService)ApplicationContextHelper.getBean(MsgInfoService.class);
	
	/**
	 * 判断系统内存使用率是否超过99%，超过则发送告警邮件
	 * @param memState
	 * @param toMail
	 * @return
	 */
	public static boolean sendWarnInfo(MemState memState){
		if(StaticKeys.NO_SEND_WARN.equals(AppInit.sendMail)){
			return false;
		}
		String key = memState.getHostname();
		if(!StringUtils.isEmpty(memState.getUsePer())&&StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))){
			try {
				double usedPer = Double.valueOf(memState.getUsePer());
				if(usedPer>HostKeys.MEM_WARN_VAL){
					String title =  title_suffix+memState.getHostname()+"内存使用率>99%告警通知";
					String commContent = "你好，你的服务器："+memState.getHostname()+",当前内存使用率为"+usedPer+"%，可能存在异常，请查看";
					String emailContent = commContent+wish_con+content_suffix;
					//发送邮件
					new EmailHandleThread(AppInit.toMail, title, emailContent).start();
					//标记已发送过告警信息
					WarnPools.MEM_WARN_MAP.put(key, "1");
					//记录发送信息
					msgInfoService.save(memState.getAccount(), memState.getAccountId(), title, commContent, AppInit.toMail);
				}
			} catch (Exception e) {
				logger.error("发送内存告警邮件失败：",e);
			}
		}
		return false;
	}
	
	/**
	 * 进程监控发送告警邮件
	 * @param appState
	 * @param appInfo
	 * @param toMail
	 * @return
	 */
	public static boolean sendAppWarnInfo(AppState appState,AppInfo appInfo){
		if(StaticKeys.NO_SEND_WARN.equals(AppInit.sendMail)){
			return false;
		}
		String key = appInfo.getAccountId()+appInfo.getHostname()+appInfo.getAppPid();
		String prefix ="";
		if(!StringUtils.isEmpty(appState.getCpuPer())&&!StringUtils.isEmpty(appState.getMemPer())&&StringUtils.isEmpty(WarnPools.APP_WARN_MAP.get(key))){
			try {
				double cpu = Double.valueOf(appState.getCpuPer());
				double mem = Double.valueOf(appState.getMemPer());
				if(cpu>HostKeys.APP_CPU_WARN_VAL&&mem<HostKeys.APP_MEM_WARN_VAL){
					prefix = "服务器："+appInfo.getHostname()+"进程："+appInfo.getAppPid()+"CPU使用率>99%告警通知";
				}else if(cpu<HostKeys.APP_CPU_WARN_VAL&&mem>HostKeys.APP_MEM_WARN_VAL){
					prefix = "服务器："+appInfo.getHostname()+"进程："+appInfo.getAppPid()+"MEM使用率>99%告警通知";
				}else if(cpu>HostKeys.APP_CPU_WARN_VAL&&mem>HostKeys.APP_MEM_WARN_VAL){
					prefix = "服务器："+appInfo.getHostname()+"进程："+appInfo.getAppPid()+"CPU使用率和MEM使用率>99%告警通知";
				}
				if(!StringUtils.isEmpty(prefix)){
					String title = title_suffix+prefix;
					String commContent = "你好，你的服务器："+appInfo.getHostname()+"，进程ID："+appInfo.getAppPid()+",当前CPU使用率为"+
							cpu+"%，MEM使用率为"+mem+"%，可能存在异常，请查看";
					String emailContent = commContent+wish_con+content_suffix;
					//发送告警邮件
					new EmailHandleThread(AppInit.toMail, title, emailContent).start();
					//标记已发送过告警信息
					WarnPools.APP_WARN_MAP.put(key, "1");
					//记录发送信息
					msgInfoService.save(appInfo.getAccount(), appInfo.getAccountId(), title, commContent, AppInit.toMail);
				}
			} catch (Exception e) {
				logger.error("发送内存告警邮件失败：",e);
			}
		}
		return false;
	}
	
}
