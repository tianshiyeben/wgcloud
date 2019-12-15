package com.core.timer.impl;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.ApplicationContextHelper;
import com.service.app.AppStateService;
import com.service.host.CpuStateService;
import com.service.host.DiskIoStateService;
import com.service.host.IntrusionInfoService;
import com.service.host.MemStateService;
import com.service.host.NetIoStateService;
import com.service.host.SysLoadStateService;
import com.service.host.TcpStateService;
import com.util.DateUtil;
import com.util.staticvar.BatchData;

/**
 *
 * @ClassName:BatchCommitJobImpl.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 每5分钟39秒时候批量提交监控数据
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class BatchCommitJobImpl extends TimerTask {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchCommitJobImpl.class);
	
	@Override
    public void run() {
		 timingTask();
    }
	
	
	public void timingTask(){
		logger.info("批量提交监控数据任务开始----------"+DateUtil.getCurrentDateTime());
		CpuStateService cpuStateService = (CpuStateService)ApplicationContextHelper.getBean(CpuStateService.class);
		MemStateService memStateService = (MemStateService)ApplicationContextHelper.getBean(MemStateService.class);
		NetIoStateService netIoStateService = (NetIoStateService)ApplicationContextHelper.getBean(NetIoStateService.class);
		SysLoadStateService sysLoadStateService = (SysLoadStateService)ApplicationContextHelper.getBean(SysLoadStateService.class);
		TcpStateService tcpStateService = (TcpStateService)ApplicationContextHelper.getBean(TcpStateService.class);
		AppStateService appStateService = (AppStateService)ApplicationContextHelper.getBean(AppStateService.class);
		DiskIoStateService diskIoStateService = (DiskIoStateService)ApplicationContextHelper.getBean(DiskIoStateService.class);
		IntrusionInfoService intrusionInfoService = (IntrusionInfoService)ApplicationContextHelper.getBean(IntrusionInfoService.class);
		try {
			synchronized(BatchData.DISKIO_STATE_LIST) {
				if(BatchData.DISKIO_STATE_LIST.size()>0){
					diskIoStateService.saveRecord(BatchData.DISKIO_STATE_LIST);
					BatchData.DISKIO_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.APP_STATE_LIST) {
				if(BatchData.APP_STATE_LIST.size()>0){
					appStateService.saveRecord(BatchData.APP_STATE_LIST);
					BatchData.APP_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.CPU_STATE_LIST) {
				if(BatchData.CPU_STATE_LIST.size()>0){
					cpuStateService.saveRecord(BatchData.CPU_STATE_LIST);
					BatchData.CPU_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.MEM_STATE_LIST) {
				if(BatchData.MEM_STATE_LIST.size()>0){
					memStateService.saveRecord(BatchData.MEM_STATE_LIST);
					BatchData.MEM_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.NETIO_STATE_LIST) {
				if(BatchData.NETIO_STATE_LIST.size()>0){
					netIoStateService.saveRecord(BatchData.NETIO_STATE_LIST);
					BatchData.NETIO_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.SYSLOAD_STATE_LIST) {
				if(BatchData.SYSLOAD_STATE_LIST.size()>0){
					sysLoadStateService.saveRecord(BatchData.SYSLOAD_STATE_LIST);
					BatchData.SYSLOAD_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.TCP_STATE_LIST) {
				if(BatchData.TCP_STATE_LIST.size()>0){
					tcpStateService.saveRecord(BatchData.TCP_STATE_LIST);
					BatchData.TCP_STATE_LIST.clear();
				}
			}
			synchronized(BatchData.INTRUSION_INFO_LIST) {
				if(BatchData.INTRUSION_INFO_LIST.size()>0){
					intrusionInfoService.saveRecord(BatchData.INTRUSION_INFO_LIST);
					BatchData.INTRUSION_INFO_LIST.clear();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("批量提交监控数据异常----------",e);
		}
		logger.info("批量提交监控数据任务结束----------"+DateUtil.getCurrentDateTime());
	}
	
	
	
}
