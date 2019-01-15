package com.entity.app;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @ClassName:AppInfo.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: app端口信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class AppInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2913111613773445949L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 用户id
	 */
	private String accountId;
	
	/**
	 * 用户帐号
	 */
	private String account;
	
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
     * 创建时间
     */
    private Date createTime; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
	
	

    
   
}