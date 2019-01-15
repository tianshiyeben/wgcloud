package com.util.staticvar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.HostInfo;


/**
 *
 * @ClassName:StaticKeys.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: StaticKeys.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class StaticKeys {
	
	public static final String ADMIN_ACCOUNT = "admin";//管理员帐号
	
	public static final String  SEARCH_START_TIME = "startTime";//日期查询开始时间条件
	
	public static final String SEARCH_END_TIME = "endTime";//日期查询结束时间条件
	
	public static final String SEARCH_EXP_TIME = "expDate";//日期查询过期时间
	
	public static final String CUR_PAGE = "curPage";//获取当前页数
	
	public static final int PAGE_SIZE = 20;//每页显示多少条记录
	
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
	
	//网站响应速度超时毫秒
	public static final long SITE_TIMEOUT = 22000;
	
	//存贮到session的手机验证码
	public static final String PHONE_RAND = "phoneRand";
	
	//存贮到session的邮箱验证码
	public static final String EMAIL_RAND = "emailRand";
	
	
	
	public static String SPLIT_BR = "</br>";//换行标识
	
	public static String SPLIT_KG = " ";//空格
	
	public static String SPLIT_DH = ",";//逗号
	
	public static String SPLIT_SXG = "//";//双反斜杠
	
	public static String HOST_PRO_FILE = "host.properties";//host信息文件
	
	public static  Date END_UPDATE_TIME = new Date();//最后获取系统状态的时间戳
	
	
	public static String ACCOUNT = "";//用户的帐号
	
	public static Integer HOST_NUM = 3;//许可host数量
	
	public static String MAC ="";//本机mac地址
	
	//存贮host信息集合，键为服务器IP
	public static List<HostInfo> hostInfoList = new ArrayList<HostInfo>();
	
	//存贮坏的hostname，每天清空一次
	public static List<String> badHostName = new ArrayList<String>();
	
}
