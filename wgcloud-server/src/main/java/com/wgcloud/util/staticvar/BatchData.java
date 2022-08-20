package com.wgcloud.util.staticvar;

import com.wgcloud.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @version v2.3
 * @ClassName:BatchData.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 临时存贮监控数据的静态工具类
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class BatchData {

    //系统信息
    public static List<SystemInfo> SYSTEM_INFO_LIST = Collections.synchronizedList(new ArrayList<SystemInfo>());


    //进程信息
    public static List<AppInfo> APP_INFO_LIST = Collections.synchronizedList(new ArrayList<AppInfo>());


    //进程状态
    public static List<AppState> APP_STATE_LIST = Collections.synchronizedList(new ArrayList<AppState>());

    //cpu监控
    public static List<CpuState> CPU_STATE_LIST = Collections.synchronizedList(new ArrayList<CpuState>());

    //内存监控
    public static List<MemState> MEM_STATE_LIST = Collections.synchronizedList(new ArrayList<MemState>());

    //网络吞吐监控，暂没用
    public static List<NetIoState> NETIO_STATE_LIST = Collections.synchronizedList(new ArrayList<NetIoState>());

    //磁盘大小
    public static List<DeskState> DESK_STATE_LIST = Collections.synchronizedList(new ArrayList<DeskState>());


    //系统负载监控
    public static List<SysLoadState> SYSLOAD_STATE_LIST = Collections.synchronizedList(new ArrayList<SysLoadState>());

    //tcp监控，暂没用
    public static List<TcpState> TCP_STATE_LIST = Collections.synchronizedList(new ArrayList<TcpState>());

    //日志信息
    public static List<LogInfo> LOG_INFO_LIST = Collections.synchronizedList(new ArrayList<LogInfo>());


    // 入侵检测信息
    public static List<IntrusionInfo> INTRUSION_INFO_LIST = Collections.synchronizedList(new ArrayList<IntrusionInfo>());


}
