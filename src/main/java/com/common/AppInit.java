package com.common;

import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.timer.impl.BatchCommitJobImpl;
import com.core.timer.impl.MonitorDayJobImpl;
import com.util.HostInfo;
import com.util.MyDayTask;
import com.util.MyHourTask;
import com.util.MyMinTask;
import com.util.PropertyUtil;
import com.util.staticvar.StaticKeys;


/**    
 *
 * @ClassName:AppInit.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: AppInit.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class AppInit implements ServletContextListener{

	private static final Logger logger = LoggerFactory.getLogger(AppInit.class);
	
	
	//邮件帐号
	public static String fromMailName = "";
	
	//邮件密码
	public static String fromPwd = "";
	
	//SMTP服务器
	public static String smtpHost = "";
	
	//SMTP端口
	public static String smtpPort="";
	
	//是否使用SSL，0不使用，1使用
	public static String smtpSSL="";
	
	//admin管理员密码
	public static String admindPwd= "111111";
	
	//是否发送邮件告警标识
	public static String sendMail = "1";
		
	//接受邮件的地址
	public static String toMail = "";
			
		
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
				
				//从配置文件读取邮箱用户名
				fromMailName = PropertyUtil.get("application.properties", "fromMailName");
				//从配置文件读取邮箱密码
				fromPwd = PropertyUtil.get("application.properties", "fromPwd");
				//从配置文件读取smtp服务器
				smtpHost = PropertyUtil.get("application.properties", "smtpHost");
				//从配置文件读取smtp端口
				smtpPort = PropertyUtil.get("application.properties", "smtpPort");
				//从配置文件读取smtpSSL标识
				smtpSSL = PropertyUtil.get("application.properties", "smtpSSL");
				//从配置文件读取管理员密码
				admindPwd =  PropertyUtil.get("application.properties", "admindPwd");
				//是否发送邮件告警标识,1发送0不发送
				sendMail =  PropertyUtil.get("application.properties", "sendMail");
				//接受邮件的地址
				toMail =  PropertyUtil.get("application.properties", "toMail");
				TimerTask dayTask = new MonitorDayJobImpl();
				TimerTask minTask = new BatchCommitJobImpl();
				Timer timer = new Timer();
				//1分钟后执行，之后每隔24小时执行
		        timer.schedule(dayTask, 60000,24*60*60*1000);
		        //10分钟后执行，之后每隔5分钟执行
		        timer.schedule(minTask, 600000,5*60*1000);
		        
		        
		      //从配置文件加载host信息
				Set<String> set = PropertyUtil.getKeys(""+StaticKeys.HOST_PRO_FILE);
				Iterator<String> iter = set.iterator();
				while(iter.hasNext()){
					HostInfo hostInfo = new HostInfo();
					hostInfo.setHostname(iter.next());
					String hostStr = PropertyUtil.get(""+StaticKeys.HOST_PRO_FILE, hostInfo.getHostname());
					if(StringUtils.isEmpty(hostStr)){
						break;
					}
					hostStr = hostStr.trim();
					hostInfo.setSshPort(Integer.valueOf(hostStr.split("//")[0]));
					hostInfo.setUsername(hostStr.split("//")[1]);
					hostInfo.setPassword(hostStr.split("//")[2]);
					StaticKeys.hostInfoList.add(hostInfo);
				}
				
				TimerTask dayClientTask = new MyDayTask();
				TimerTask hourClientTask = new MyHourTask();
				TimerTask minClientTask = new MyMinTask();
		        Timer timerClient = new Timer();
		        //2分钟后执行，之后每隔2小时执行
		        timerClient.schedule(hourClientTask, 120000,2*60*60*1000);
		        //9分钟后执行，之后每隔24小时执行
		        timerClient.schedule(dayClientTask, 540000,24*60*60*1000);
		       //16分钟后执行，之后每隔10分钟执行
		        timerClient.schedule(minClientTask, 960000,10*60*1000);
	}
 
}
