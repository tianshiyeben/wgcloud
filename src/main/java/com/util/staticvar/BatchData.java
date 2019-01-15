package com.util.staticvar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.entity.app.AppState;
import com.entity.host.CpuState;
import com.entity.host.DiskIoState;
import com.entity.host.IntrusionInfo;
import com.entity.host.MemState;
import com.entity.host.NetIoState;
import com.entity.host.SysLoadState;
import com.entity.host.TcpState;



/**
 *
 * @ClassName:BatchData.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 临时存贮监控数据的静态工具类
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class BatchData {
	
	//进程监控
	public static List<AppState> APP_STATE_LIST = Collections.synchronizedList(new ArrayList<AppState>());
	
	//cpu监控
	public static  List<CpuState> CPU_STATE_LIST = Collections.synchronizedList(new ArrayList<CpuState>());
	
	//内存监控
	public static List<MemState> MEM_STATE_LIST = Collections.synchronizedList(new ArrayList<MemState>());
	
	//网络吞吐监控
	public static List<NetIoState> NETIO_STATE_LIST =  Collections.synchronizedList(new ArrayList<NetIoState>());
	
	//磁盘IO监控
	public static List<DiskIoState> DISKIO_STATE_LIST =  Collections.synchronizedList(new ArrayList<DiskIoState>());
	
	//系统负载监控
	public static List<SysLoadState> SYSLOAD_STATE_LIST = Collections.synchronizedList(new ArrayList<SysLoadState>());
	
	//tcp监控
	public static List<TcpState> TCP_STATE_LIST = Collections.synchronizedList(new ArrayList<TcpState>());
	
	
	// 入侵检测信息
	public static List<IntrusionInfo> INTRUSION_INFO_LIST = Collections.synchronizedList(new ArrayList<IntrusionInfo>());
		
		
	/**
	 * 清空所有list
	 */
	public static void clearList(){
		synchronized(APP_STATE_LIST) {
			APP_STATE_LIST.clear();
		}
		synchronized(CPU_STATE_LIST) {
			CPU_STATE_LIST.clear();
		}
		synchronized(MEM_STATE_LIST) {
			MEM_STATE_LIST.clear();
		}
		synchronized(NETIO_STATE_LIST) {
			NETIO_STATE_LIST.clear();
		}
		synchronized(SYSLOAD_STATE_LIST) {
			SYSLOAD_STATE_LIST.clear();
		}
		synchronized(TCP_STATE_LIST) {
			TCP_STATE_LIST.clear();
		}
		synchronized(INTRUSION_INFO_LIST) {
			INTRUSION_INFO_LIST.clear();
		}
	}
	
}
