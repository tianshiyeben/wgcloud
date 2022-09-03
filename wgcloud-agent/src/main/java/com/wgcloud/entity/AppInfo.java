package com.wgcloud.entity;

import java.util.Date;

/**
 * @version V2.3
 * @ClassName:AppInfo.java
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: app端口信息
 * @Copyright: 2017-2022 www.wgstart.com. All rights reserved.
 */
public class AppInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 应用进程ID
     */
    private String appPid;

    /**
     * 应用进程名称
     */
    private String appName;

    /**
     * 内存使用M
     */
    private Double memPer;

    /**
     * cpu使用率
     */
    private Double cpuPer;


    /**
     * 进程获取途径，1进程id号，2进程pid文件
     */
    private String appType;


    /**
     * 进程状态，1正常，2下线
     */
    private String state;

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


    public String getAppPid() {
        return appPid;
    }

    public void setAppPid(String appPid) {
        this.appPid = appPid;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Double getMemPer() {
        return memPer;
    }

    public void setMemPer(Double memPer) {
        this.memPer = memPer;
    }

    public Double getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(Double cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}