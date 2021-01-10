package com.wgcloud.util.msg;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName:WarnPools.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: WarnPools.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
public class WarnPools {
	

	/**
	 * 存贮每天发送的内存告警信息map<用户ID+服务器IP，1>
	 */
	public static Map<String,String> MEM_WARN_MAP = new HashMap<String,String>();
	
	public static void clearOldData(){
		MEM_WARN_MAP.clear();
	}

}
