package com.wgcloud.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName:LinuxCmd.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: LinuxCmd.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
public class LinuxCmd {

	public static final String VIEW_MEM = "free -m";
	
	public static final String SYSTEM_RELEASE = "cat /etc/system-release";
	
	public static final String UNAME_A = "uname -a";
	
	public static final String DF_HL = "df -hlP";
	
	
	public static final String WULI_CPU_NUM = "cat /proc/cpuinfo| grep \"physical id\"| sort| uniq| wc -l";
	
	public static final String WULI_CPU_CORE_NUM = "cat /proc/cpuinfo| grep \"cpu cores\"| uniq";
	
	public static final String CPU_XINGHAO = "cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c";
	
	public static final String VMSTAT = "mpstat -P ALL 1 3";
	
	public static final String SAR_DEV_1 = "sar -n DEV 1 3";
	
	public static final String DISK_IO = "iostat -xkz 1 1";
	
	
//	public static final String SAR_TCP_1 = "sar -n TCP,ETCP 1 1";
	
	public static final String SAR_TCP_1 = "sar -n TCP 1 3";
	
	public static final String UPTIME = "uptime";
	
	public static final String dd = "ps aux|head -1;ps aux|grep {pid}";
	
	public static List<String> ddList = new ArrayList();
	
	public static final String rpcinfo = "rpcinfo -p";//看rpc服务开放
	
	public static final String lsmod = "lsmod";//检查系统内核模块
	
	public static final String passwd_update_time = "ls -l /etc/passwd";//查看passwd文件修改时间
	
	public static final String crontab = "cat /etc/crontab";//查看计划任务
	
	public static final String promisc = "ip link | grep promisc";//检查网络：ip link | grep PROMISC（正常网卡不该在promisc模式，可能存在sniffer）
	
	
}
