package com.util.msg;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName:WarnPools.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: WarnPools.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class WarnPools {
	
	/**
	 * 存贮标识测速失败的网站map<httpinfoId，1>
	 */
	public static Map<String,String> HTTP_TIMEOUT_MAP = new HashMap<String,String>();

	/**
	 * 存贮每天发送的内存告警信息map<用户ID+服务器IP，1>
	 */
	public static Map<String,String> MEM_WARN_MAP = new HashMap<String,String>();
	
	
	/**
	 * 存贮每天发送的进程监控告警信息map<用户ID+服务器IP+进程ID，1>
	 */
	public static Map<String,String> APP_WARN_MAP = new HashMap<String,String>();
	
	
	/**
	 * 存贮每天发送的网站监控告警信息map<用户ID，1>
	 */
	public static Map<String,String> HTTP_WARN_MAP = new HashMap<String,String>();
	
	
	public static void clearOldData(){
		HTTP_TIMEOUT_MAP.clear();
		MEM_WARN_MAP.clear();
		APP_WARN_MAP.clear();
		HTTP_WARN_MAP.clear();
	}
}
