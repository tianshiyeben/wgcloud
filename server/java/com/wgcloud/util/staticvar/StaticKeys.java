package com.wgcloud.util.staticvar;


import com.wgcloud.entity.MailSet;

/**
 *
 * @ClassName:StaticKeys.java     
 * @version v2.1
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: StaticKeys.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
public class StaticKeys {
	
	public static final String ADMIN_ACCOUNT = "admin";//管理员帐号
	
	public static final String  SEARCH_START_TIME = "startTime";//日期查询开始时间条件
	
	public static final String SEARCH_END_TIME = "endTime";//日期查询结束时间条件
	
	public static final String SEARCH_EXP_TIME = "expDate";//日期查询过期时间
	
	public static String MENU_ACTIVE = "active ";//菜单高亮显示
	
	public static String APP_PRO_FILE = "application.properties";//配置信息文件
	
	//session中存贮的验证码key
	public static final String SESSION_CODE = "valcode";
	
	//session存贮登录信息的标识
	public static String LOGIN_KEY = "LOGIN_KEY";
	
	//日志成功标记
	public static final String LOG_SUCCESS = "0";
	
	//日志失败标记
	public static final String LOG_ERROR = "1";
	
	// 00:00:00
	public static final String MIN_SECOND_START = " 00:00:00";
	
	// 23:59:59
	public static final String MIN_SECOND_END = " 23:59:59";
	
	//链接服务器错误标识
	public static final String CONN_HOST_ERROR = "connerror";
	
	//发送报警短信邮件标识
	public static final String SEND_WARN = "1";
	
	//不发送报警短信邮件标识
	public static final String NO_SEND_WARN = "0";
	
	public static String SPLIT_BR = "</br>";//换行标识
	
	public static String SPLIT_KG = " ";//空格
	
	public static String SPLIT_DH = ",";//逗号
	
	public static String SPLIT_SXG = "//";//双反斜杠

	public static MailSet mailSet = null;

}
