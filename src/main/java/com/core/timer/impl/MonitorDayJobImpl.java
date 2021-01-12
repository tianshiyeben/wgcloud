package com.core.timer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.ApplicationContextHelper;
import com.dao.app.AppInfoDao;
import com.dao.app.AppStateDao;
import com.dao.host.CpuStateDao;
import com.dao.host.DeskStateDao;
import com.dao.host.DiskIoStateDao;
import com.dao.host.IntrusionInfoDao;
import com.dao.host.MemStateDao;
import com.dao.host.NetIoStateDao;
import com.dao.host.SysLoadStateDao;
import com.dao.host.SystemInfoDao;
import com.dao.host.TcpStateDao;
import com.dao.log.LogInfoDao;
import com.dao.msg.MsgInfoDao;
import com.entity.app.AppInfo;
import com.util.DateUtil;
import com.util.msg.WarnPools;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:MonitorDayJobImpl.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 每天1点删除历史信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MonitorDayJobImpl extends TimerTask {
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorDayJobImpl.class);
	
	
	 @Override
     public void run() {
		 timingTask();
     }
	 
	public void timingTask(){
		logger.info("定时清空历史数据任务开始----------"+DateUtil.getCurrentDateTime());
		WarnPools.clearOldData();//清空发告警邮件的记录
		String nowTime = DateUtil.getCurrentDateTime();
		//3天前时间
		String oneDayBefore = DateUtil.getDateBefore(nowTime, 3);
		//30天前时间
		String sevenDayBefore = DateUtil.getDateBefore(nowTime, 30);
		Map<String, Object> paramsDel = new HashMap<String,Object>();
		SystemInfoDao systemInfoDao = (SystemInfoDao)ApplicationContextHelper.getBean(SystemInfoDao.class);
		CpuStateDao cpuStateDao = (CpuStateDao)ApplicationContextHelper.getBean(CpuStateDao.class);
		DeskStateDao deskStateDao = (DeskStateDao)ApplicationContextHelper.getBean(DeskStateDao.class);
		MemStateDao memStateDao = (MemStateDao)ApplicationContextHelper.getBean(MemStateDao.class);
		NetIoStateDao netIoStateDao = (NetIoStateDao)ApplicationContextHelper.getBean(NetIoStateDao.class);
		SysLoadStateDao sysLoadStateDao = (SysLoadStateDao)ApplicationContextHelper.getBean(SysLoadStateDao.class);
		TcpStateDao tcpStateDao = (TcpStateDao)ApplicationContextHelper.getBean(TcpStateDao.class);
		AppInfoDao appInfoDao = (AppInfoDao)ApplicationContextHelper.getBean(AppInfoDao.class);
		AppStateDao appStateDao = (AppStateDao)ApplicationContextHelper.getBean(AppStateDao.class);
		DiskIoStateDao diskIoStateDao = (DiskIoStateDao)ApplicationContextHelper.getBean(DiskIoStateDao.class);
		IntrusionInfoDao intrusionInfoDao = (IntrusionInfoDao)ApplicationContextHelper.getBean(IntrusionInfoDao.class);
		List<AppInfo> appInfoList = null;
		try {
			appInfoList = appInfoDao.selectAllByParams(null);
			paramsDel.put(StaticKeys.SEARCH_END_TIME,sevenDayBefore);
			paramsDel.put("accountId",StaticKeys.ADMIN_ACCOUNT);
			
			//执行删除操作begin
			if(paramsDel.get(StaticKeys.SEARCH_END_TIME)!=null&&!"".equals(paramsDel.get(StaticKeys.SEARCH_END_TIME))){
				cpuStateDao.deleteByAccountAndDate(paramsDel);//删除cpu监控信息
				deskStateDao.deleteByAccountAndDate(paramsDel);//删除磁盘监控信息
				memStateDao.deleteByAccountAndDate(paramsDel);//删除内存监控信息
				netIoStateDao.deleteByAccountAndDate(paramsDel);//删除吞吐率监控信息
				sysLoadStateDao.deleteByAccountAndDate(paramsDel);//删除负载状态监控信息
				tcpStateDao.deleteByAccountAndDate(paramsDel);//删除tcp监控信息
				diskIoStateDao.deleteByAccountAndDate(paramsDel);//删除磁盘IO监控信息
				//删除进程监控信息
				if(appInfoList.size()>0){
					paramsDel.put("appInfoIds",  getAppInfoIdList(appInfoList));
					appStateDao.deleteByAppInfoIdsAndDate(paramsDel);
				}
				//删除3天前服务器系统信息和入侵检测信息
				paramsDel.put(StaticKeys.SEARCH_END_TIME,oneDayBefore);
				systemInfoDao.deleteByAccountAndDate(paramsDel);
				intrusionInfoDao.deleteByAccountAndDate(paramsDel);
			}
			//执行删除操作end
			
			//删除30天前的日志信息
			LogInfoDao logInfoDao = (LogInfoDao)ApplicationContextHelper.getBean(LogInfoDao.class);
			String thrityDayBefore = DateUtil.getDateBefore(nowTime, 30);
			paramsDel.put(StaticKeys.SEARCH_END_TIME,thrityDayBefore);
			logInfoDao.deleteByDate(paramsDel);
			
			//删除30天前的信息发送记录
			MsgInfoDao msgInfoDao = (MsgInfoDao)ApplicationContextHelper.getBean(MsgInfoDao.class);
			msgInfoDao.deleteByDate(paramsDel);
			
			
		} catch (Exception e) {
			logger.info("定时清空历史数据任务出错：",e);
		}
		logger.info("定时清空历史数据任务结束----------"+DateUtil.getCurrentDateTime());
	}
	
	
	/**
	 * 取出AppInfo集合中的id
	 * @param list
	 * @return
	 */
	private List<String> getAppInfoIdList(List<AppInfo> list){
		List<String> idList = new ArrayList<String>();
		for(AppInfo info : list){
			idList.add(info.getId());
		}
		return idList;
	}
	
}
