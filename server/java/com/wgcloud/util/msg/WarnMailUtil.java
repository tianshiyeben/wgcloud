package com.wgcloud.util.msg;

import com.wgcloud.common.ApplicationContextHelper;
import com.wgcloud.config.CommonConfig;
import com.wgcloud.entity.CpuState;
import com.wgcloud.entity.HeathMonitor;
import com.wgcloud.entity.MailSet;
import com.wgcloud.entity.MemState;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 * @ClassName:WarnMailUtil.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: WarnMailUtil.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
public class WarnMailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WarnMailUtil.class);

	public static final String content_suffix = "<p><a target='_blank' href='http://www.wgstart.com'>WGCLOUD</a>敬上";
	
	private static LogInfoService logInfoService = (LogInfoService) ApplicationContextHelper.getBean(LogInfoService.class);
    private static CommonConfig commonConfig = (CommonConfig) ApplicationContextHelper.getBean(CommonConfig.class);



    /**
	 * 判断系统内存使用率是否超过98%，超过则发送告警邮件
	 * @param memState
	 * @param toMail
	 * @return
	 */
	public static boolean sendWarnInfo(MemState memState){
		if(StaticKeys.mailSet==null){
			return false;
		}
		MailSet mailSet = StaticKeys.mailSet;
		if(StaticKeys.NO_SEND_WARN.equals(mailSet.getSendMail())){
			return false;
		}
		String key = memState.getHostname();
        if(!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))){
            return false;
        }
		if(memState.getUsePer()!=null && memState.getUsePer()>=commonConfig.getMemWarnVal()){
			try {
					String title = "内存告警："+ memState.getHostname();
					String commContent = "服务器："+memState.getHostname()+",内存使用率为"+Double.valueOf(memState.getUsePer())+"%，可能存在异常，请查看";
					//发送邮件
                    sendMail(mailSet.getToMail(),title,commContent);
					//标记已发送过告警信息
					WarnPools.MEM_WARN_MAP.put(key, "1");
					//记录发送信息
					logInfoService.save( title, commContent,StaticKeys.LOG_ERROR);
			} catch (Exception e) {
				logger.error("发送内存告警邮件失败：",e);
				logInfoService.save("发送内存告警邮件错误",e.toString(),StaticKeys.LOG_ERROR);
			}
		}

		return false;
	}

    /**
     * 判断系统cpu使用率是否超过98%，超过则发送告警邮件
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendCpuWarnInfo(CpuState cpuState){
        if(StaticKeys.mailSet==null){
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if(StaticKeys.NO_SEND_WARN.equals(mailSet.getSendMail())){
            return false;
        }
        String key = cpuState.getHostname();
        if(!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))){
            return false;
        }
        if(cpuState.getSys()!=null && cpuState.getSys()>=commonConfig.getCpuWarnVal()){
            try {
                String title =  "CPU告警："+cpuState.getHostname();
                String commContent = "服务器："+cpuState.getHostname()+",CPU使用率为"+Double.valueOf(cpuState.getSys())+"%，可能存在异常，请查看";
                //发送邮件
                sendMail(mailSet.getToMail(),title,commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save( title, commContent,StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送内存告警邮件失败：",e);
                logInfoService.save("发送内存告警邮件错误",e.toString(),StaticKeys.LOG_ERROR);
            }
        }

        return false;
    }


    /**
     * 服务接口不通发送告警邮件
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendHeathInfo(HeathMonitor heathMonitor){
        if(StaticKeys.mailSet==null){
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if(StaticKeys.NO_SEND_WARN.equals(mailSet.getSendMail())){
            return false;
        }
        String key = heathMonitor.getId();
        if(!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))){
            return false;
        }
        try {
            String title =  "服务接口检测告警："+heathMonitor.getAppName();
            String commContent = "服务接口："+heathMonitor.getHeathUrl()+"，响应状态码为"+heathMonitor.getHeathStatus()+"，可能存在异常，请查看";
            //发送邮件
            sendMail(mailSet.getToMail(),title,commContent);
            //标记已发送过告警信息
            WarnPools.MEM_WARN_MAP.put(key, "1");
            //记录发送信息
            logInfoService.save( title, commContent, StaticKeys.LOG_ERROR);
        } catch (Exception e) {
            logger.error("发送服务健康检测告警邮件失败：",e);
            logInfoService.save("发送服务健康检测告警邮件错误",e.toString(),StaticKeys.LOG_ERROR);
        }
        return false;
    }

    private   static void sendMail(String mails,String mailTitle,String mailContent){
        try {
                HtmlEmail email = new HtmlEmail ();
                email.setHostName(StaticKeys.mailSet.getSmtpHost());
                email.setSmtpPort(Integer.valueOf(StaticKeys.mailSet.getSmtpPort()));
                if("1".equals(StaticKeys.mailSet.getSmtpSSL())){
                    email.setSSL(true);
                }
                email.setAuthenticator(new DefaultAuthenticator(StaticKeys.mailSet.getFromMailName(), StaticKeys.mailSet.getFromPwd()));
                email.setFrom(StaticKeys.mailSet.getFromMailName(), "WGCLOUD");//发信者
                email.setSubject(mailTitle);//标题
                email.setCharset("UTF-8");//编码格式
                email.setHtmlMsg(mailContent+content_suffix);//内容
                email.addTo(mails.split(";"));
                email.setSentDate(new Date());
                email.send();//发送
        } catch (Exception e) {
            logger.error("发送邮件错误：",e);
            logInfoService.save("发送邮件错误",e.toString(),StaticKeys.LOG_ERROR);
        }
    }


	

}
