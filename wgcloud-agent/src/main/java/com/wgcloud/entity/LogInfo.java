package com.wgcloud.entity;

import java.util.Date;

/**
 * @version V2.3
 * @ClassName:LogInfo.java
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: 日志信息
 * @Copyright: 2017-2022 www.wgstart.com. All rights reserved.
 */
public class LogInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1565538727002722890L;

    /**
     * host名称
     */
    private String hostname;

    /**
     * 描述
     */
    private String infoContent;

    /**
     * 0成功，1失败
     */
    private String state;


    /**
     * 创建时间
     */
    private Date createTime;


    public String getHostname() {
        return hostname;
    }


    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


    public String getInfoContent() {
        return infoContent;
    }


    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}