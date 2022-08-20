package com.wgcloud.util.msg;

import com.wgcloud.common.ApplicationContextHelper;
import com.wgcloud.config.MailConfig;
import com.wgcloud.entity.*;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:WarnMailUtil.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: WarnMailUtil.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class WarnMailUtil {

    private static final Logger logger = LoggerFactory.getLogger(WarnMailUtil.class);

    public static final String content_suffix = "<p><a target='_blank' href='http://www.wgstart.com'>WGCLOUD</a>敬上";

    private static LogInfoService logInfoService = (LogInfoService) ApplicationContextHelper.getBean(LogInfoService.class);
    private static MailConfig mailConfig = (MailConfig) ApplicationContextHelper.getBean(MailConfig.class);


    /**
     * 判断系统内存使用率是否超过98%，超过则发送告警邮件
     *
     * @param memState
     * @param toMail
     * @return
     */
    public static boolean sendWarnInfo(MemState memState) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getMemWarnMail())) {
            return false;
        }
        String key = memState.getHostname();
        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
            return false;
        }
        if (memState.getUsePer() != null && memState.getUsePer() >= mailConfig.getMemWarnVal()) {
            try {
                String title = "内存告警：" + memState.getHostname();
                String commContent = "服务器：" + memState.getHostname() + ",内存使用率为" + Double.valueOf(memState.getUsePer()) + "%，可能存在异常，请查看";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送内存告警邮件失败：", e);
                logInfoService.save("发送内存告警邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        }

        return false;
    }

    /**
     * 判断系统cpu使用率是否超过98%，超过则发送告警邮件
     *
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendCpuWarnInfo(CpuState cpuState) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getCpuWarnMail())) {
            return false;
        }
        String key = cpuState.getHostname();
        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
            return false;
        }
        if (cpuState.getSys() != null && cpuState.getSys() >= mailConfig.getCpuWarnVal()) {
            try {
                String title = "CPU告警：" + cpuState.getHostname();
                String commContent = "服务器：" + cpuState.getHostname() + ",CPU使用率为" + Double.valueOf(cpuState.getSys()) + "%，可能存在异常，请查看";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送内存告警邮件失败：", e);
                logInfoService.save("发送内存告警邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        }

        return false;
    }


    /**
     * 服务接口不通发送告警邮件
     *
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendHeathInfo(HeathMonitor heathMonitor, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getHeathWarnMail())) {
            return false;
        }
        String key = heathMonitor.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "服务接口检测告警：" + heathMonitor.getAppName();
                String commContent = "服务接口：" + heathMonitor.getHeathUrl() + "，响应状态码为" + heathMonitor.getHeathStatus() + "，可能存在异常，请查看";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送服务健康检测告警邮件失败：", e);
                logInfoService.save("发送服务健康检测告警邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "服务接口恢复正常通知：" + heathMonitor.getAppName();
                String commContent = "服务接口恢复正常通知：" + heathMonitor.getHeathUrl() + "，响应状态码为" + heathMonitor.getHeathStatus() + "";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送服务接口恢复正常通知邮件失败：", e);
                logInfoService.save("发送服务接口恢复正常通知邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    /**
     * 主机下线发送告警邮件
     *
     * @param systemInfo 主机信息
     * @param isDown     是否是下线告警，true下线告警，false上线恢复
     * @return
     */
    public static boolean sendHostDown(SystemInfo systemInfo, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getHostDownWarnMail())) {
            return false;
        }
        String key = systemInfo.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "主机下线告警：" + systemInfo.getHostname();
                String commContent = "主机已经超过10分钟未上报数据，可能已经下线：" + systemInfo.getHostname() + "，备注：" + systemInfo.getRemark()
                        + "。如果不再监控该主机在列表删除即可，同时不会再收到该主机告警邮件";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送主机下线告警邮件失败：", e);
                logInfoService.save("发送主机下线告警邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "主机恢复上线通知：" + systemInfo.getHostname();
                String commContent = "主机已经恢复上线：" + systemInfo.getHostname() + "，备注：" + systemInfo.getRemark()
                        + "。";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送主机恢复上线通知邮件失败：", e);
                logInfoService.save("发送主机恢复上线通知邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    /**
     * 进程下线发送告警邮件
     *
     * @param AppInfo 进程信息
     * @param isDown  是否是下线告警，true下线告警，false上线恢复
     * @return
     */
    public static boolean sendAppDown(AppInfo appInfo, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getAppDownWarnMail())) {
            return false;
        }
        String key = appInfo.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "进程下线告警：" + appInfo.getHostname() + "，" + appInfo.getAppName();
                String commContent = "进程已经超过10分钟未上报数据，可能已经下线：" + appInfo.getHostname() + "，" + appInfo.getAppName()
                        + "。如果不再监控该进程在列表删除即可，同时不会再收到该进程告警邮件";
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //标记已发送过告警信息
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送进程下线告警邮件失败：", e);
                logInfoService.save("发送进程下线告警错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "进程恢复上线通知：" + appInfo.getHostname() + "，" + appInfo.getAppName();
                String commContent = "进程恢复上线通知：" + appInfo.getHostname() + "，" + appInfo.getAppName();
                //发送邮件
                sendMail(mailSet.getToMail(), title, commContent);
                //记录发送信息
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("发送进程恢复上线通知邮件失败：", e);
                logInfoService.save("发送进程恢复上线通知错误", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    public static String sendMail(String mails, String mailTitle, String mailContent) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(StaticKeys.mailSet.getSmtpHost());
            email.setSmtpPort(Integer.valueOf(StaticKeys.mailSet.getSmtpPort()));
            if ("1".equals(StaticKeys.mailSet.getSmtpSSL())) {
                email.setSSL(true);
            }
            email.setAuthenticator(new DefaultAuthenticator(StaticKeys.mailSet.getFromMailName(), StaticKeys.mailSet.getFromPwd()));
            email.setFrom(StaticKeys.mailSet.getFromMailName());//发信者
            email.setSubject("[WGCLOUD] " + mailTitle);//标题
            email.setCharset("UTF-8");//编码格式
            email.setHtmlMsg(mailContent + content_suffix);//内容
            email.addTo(mails.split(";"));
            email.setSentDate(new Date());
            email.send();//发送
            return "success";
        } catch (Exception e) {
            logger.error("发送邮件错误：", e);
            logInfoService.save("发送邮件错误", e.toString(), StaticKeys.LOG_ERROR);
            return "error";
        }
    }


}
