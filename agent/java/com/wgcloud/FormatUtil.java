package com.wgcloud;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtil {

    private static Logger logger  = LoggerFactory.getLogger(ScheduledTask.class);

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化double数据，截取小数点后数字
     * @param str
     * @param num
     * @return
     */
    public static double formatDouble(double str,int num){
        java.math.BigDecimal b = new java.math.BigDecimal(str);
        double myNum3 = b.setScale(num,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        return myNum3;
    }

    public static String delChar(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }
        str = str.replace("%","");
        return str;
    }

    /**
     * 获取当前时间
     * @return 当前日期
     */
    public static Timestamp getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_PATTERN);
        Timestamp nowTime = Timestamp.valueOf(dateFormat.format(new Date()));
        return nowTime;
    }

    public static Date getDateBefore(Date datetimes,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(datetimes);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }


}
