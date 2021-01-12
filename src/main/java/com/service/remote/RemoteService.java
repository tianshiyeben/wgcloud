package com.service.remote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.entity.app.AppInfo;
import com.entity.app.AppState;
import com.service.app.AppStateService;
import com.util.DateUtil;
import com.util.msg.WarnMailUtil;
import com.util.staticvar.BatchData;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:RemoteService.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 远程客户端提交信息管理
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class RemoteService {

	private static final Logger logger = LoggerFactory.getLogger(RemoteService.class);
	
	/**
	 * 保存客户端提交的应用进程监控信息
	 * @param request
	 * @param accountInfo
	 * @param appInfoList
	 * @param appStateService
	 * @throws Exception 
	 */
	public void setHostAppInfo(HttpServletRequest request,List<AppInfo> appInfoList,
			AppStateService appStateService) throws Exception{
		String remoteStr = "";
		String[] appStateStr = null;
		List<AppState> recordList = new ArrayList<AppState>();
		Timestamp t = DateUtil.getNowTime();
		for(AppInfo appInfo : appInfoList){
			remoteStr = request.getParameter("host"+appInfo.getHostname().substring(appInfo.getHostname().length()-1)+appInfo.getAppPid());
			if(!StringUtils.isEmpty(remoteStr)){
				appStateStr = remoteStr.split(StaticKeys.SPLIT_KG);
				if(appStateStr.length>1){
					AppState appState = new AppState();
					appState.setCpuPer(appStateStr[0]);
					appState.setMemPer(appStateStr[1]);
					appState.setAppInfoId(appInfo.getId());
					appState.setCreateTime(t);
					synchronized(BatchData.APP_STATE_LIST) {
						BatchData.APP_STATE_LIST.add(appState);
					}
					//判断是否需要发送告警邮件
					WarnMailUtil.sendAppWarnInfo(appState,appInfo);
				}
			}
		}
	}
}
