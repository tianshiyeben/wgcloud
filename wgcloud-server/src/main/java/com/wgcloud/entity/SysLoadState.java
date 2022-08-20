package com.wgcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:SysLoadState.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: uptime查看系统负载状态
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class SysLoadState extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -4863071148000213553L;

    /**
     * host名称
     */
    private String hostname;

    /**
     * 1分钟之前到现在的负载
     */
    private Double oneLoad;

    /**
     * 5分钟之前到现在的负载
     */
    private Double fiveLoad;

    /**
     * 15分钟之前到现在的负载
     */
    private Double fifteenLoad;

    /**
     * 登录用户数量 废弃
     */
    private String users;

    /**
     * 添加时间
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * 创建时间
     */
    private Date createTime;


    public Double getOneLoad() {
        return oneLoad;
    }

    public void setOneLoad(Double oneLoad) {
        this.oneLoad = oneLoad;
    }

    public Double getFiveLoad() {
        return fiveLoad;
    }

    public void setFiveLoad(Double fiveLoad) {
        this.fiveLoad = fiveLoad;
    }

    public Double getFifteenLoad() {
        return fifteenLoad;
    }

    public void setFifteenLoad(Double fifteenLoad) {
        this.fifteenLoad = fifteenLoad;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(11);
        }
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


}