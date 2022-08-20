package com.wgcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:DiskIoState.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 查看磁盘IO使用情况
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class MailSet extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -8284741180883299533L;


    /**
     * 是否发送邮件告警,1发送0不发送
     */
    private String sendMail;

    /**
     * 发送邮箱的帐号
     */
    private String fromMailName;

    /**
     * 发送邮箱的密码
     */
    private String fromPwd;

    /**
     * 发送邮箱的SMTP服务器
     */
    private String smtpHost;

    /**
     * 发送邮箱的SMTP端口,25或465
     */
    private String smtpPort;

    /**
     * 发送邮箱是否启用安全链接(SSL),1启用,0不启用
     */
    private String smtpSSL;

    /**
     * 接受告警信息的邮件
     */
    private String toMail;

    /**
     * cpu使用率告警值
     */
    private String cpuPer;
    /**
     * mem使用率告警值
     */
    private String memPer;
    /**
     * mem使用率告警值
     */
    private String heathPer;


    /**
     * 创建时间
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendMail() {
        return sendMail;
    }

    public void setSendMail(String sendMail) {
        this.sendMail = sendMail;
    }

    public String getFromMailName() {
        return fromMailName;
    }

    public void setFromMailName(String fromMailName) {
        this.fromMailName = fromMailName;
    }

    public String getFromPwd() {
        return fromPwd;
    }

    public void setFromPwd(String fromPwd) {
        this.fromPwd = fromPwd;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpSSL() {
        return smtpSSL;
    }

    public void setSmtpSSL(String smtpSSL) {
        this.smtpSSL = smtpSSL;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(String cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getMemPer() {
        return memPer;
    }

    public void setMemPer(String memPer) {
        this.memPer = memPer;
    }

    public String getHeathPer() {
        return heathPer;
    }

    public void setHeathPer(String heathPer) {
        this.heathPer = heathPer;
    }
}