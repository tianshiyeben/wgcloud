package com.wgcloud.entity;

import java.sql.Timestamp;

/**
 * @version v2.3
 * @ClassName:DbInfo.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 检查系统入侵信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DbInfo extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * 连接别名
     */
    private String aliasName;


    /**
     * 数据库类型，mysql或则oracle
     */
    private String dbType;

    /**
     * 数据库用户名
     */
    private String user;

    /**
     * 数据库密码
     */
    private String passwd;

    /**
     * 数据库服务器ip
     */
    private String ip;

    /**
     * 数据库端口
     */
    private String port;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库连接状态,1正常，2失败
     */
    private String dbState;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbState() {
        return dbState;
    }

    public void setDbState(String dbState) {
        this.dbState = dbState;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}