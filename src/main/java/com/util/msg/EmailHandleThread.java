package com.util.msg;

import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.AppInit;
import com.util.staticvar.StaticKeys;


/**
 *
 * @ClassName:EmailHandleThread.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: EmailHandleThread.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class EmailHandleThread extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailHandleThread.class);
	
	//接受的邮件地址
	public String mails = null;
	
	//邮件标题
	public String mailTitle = "";
	
	//邮件正文
	public String mailContent = "";
	
	public EmailHandleThread(){
	}
	
	public EmailHandleThread(String mails,String mailTitle,String mailContent){
		this.mails=mails;
		this.mailTitle=mailTitle;
		this.mailContent=mailContent;
	}
	

	public void run(){
		try {
			String[] mail = mails.split(StaticKeys.SPLIT_DH);
			for(int i =0 ;i < mail.length;i++){
				HtmlEmail  email = new HtmlEmail ();
				email.setHostName(AppInit.smtpHost);
				email.setSmtpPort(Integer.valueOf(AppInit.smtpPort));
				if("1".equals(AppInit.smtpSSL)){
					email.setSSL(true);
				}
				email.setAuthenticator(new DefaultAuthenticator(AppInit.fromMailName, AppInit.fromPwd));
		    	email.setFrom(AppInit.fromMailName, "WGCLOUD");//发信者 
				email.setSubject(mailTitle);//标题 
				email.setCharset("UTF-8");//编码格式 
				email.setHtmlMsg(mailContent);//内容 
				email.addTo(mail[i],"");
				email.setSentDate(new Date());
				email.send();//发送 
			}
		} catch (Exception e) {
			logger.error("发送邮件错误：",e);
		}
		
	}
	
	private static String getMailHost(String fromMailName){
		return fromMailName.substring(fromMailName.lastIndexOf("@")+1);
	}
	
    
}
