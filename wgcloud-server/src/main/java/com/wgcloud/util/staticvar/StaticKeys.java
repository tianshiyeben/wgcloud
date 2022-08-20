package com.wgcloud.util.staticvar;


import com.wgcloud.entity.MailSet;

/**
 * @version v2.3
 * @ClassName:StaticKeys.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: StaticKeys.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class StaticKeys {

    public static final String ADMIN_ACCOUNT = "admin";//管理员帐号

    public static final String SEARCH_START_TIME = "startTime";//日期查询开始时间条件

    public static final String SEARCH_END_TIME = "endTime";//日期查询结束时间条件

    public static final String SEARCH_EXP_TIME = "expDate";//日期查询过期时间

    public static String MENU_ACTIVE = "active ";//菜单高亮显示

    public static String APP_PRO_FILE = "application.properties";//配置信息文件

    //session中存贮的验证码key
    public static final String SESSION_CODE = "valcode";

    //session存贮登录信息的标识
    public static String LOGIN_KEY = "LOGIN_KEY";

    public static String DASH_VIEW_ACCOUNT = "dashView";


    //日志失败标记
    public static final String LOG_ERROR = "1";

    //不发送报警短信邮件标识
    public static final String NO_SEND_WARN = "false";

    public static String SPLIT_BR = "</br>";//换行标识

    public static String SPLIT_KG = " ";//空格

    public static String SPLIT_DH = ",";//逗号

    public static String SPLIT_SXG = "//";//双反斜杠

    public static String DOWN_STATE = "2";

    public static MailSet mailSet = null;

}
