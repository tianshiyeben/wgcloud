package com.entity.host;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @ClassName:IntrusionInfo.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 检查系统入侵信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class IntrusionInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 879979812204191283L;

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
	 * 系统内核模块
	 */
    private String lsmod;

    /**
	 *查看passwd文件修改时间
	 */
    private String passwdInfo;
    
    /**
	 * 查看系统计划任务
	 */
    private String crontab;
    
    /**
   	 * 检查网络，正常网卡不该在promisc模式，可能存在sniffer
   	 */
    private String promisc;
    
    /**
     * 系统rpc服务
     */
    private String rpcinfo;
    
    
    /**
     * 创建时间
     */
    private Timestamp createTime; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	public String getLsmod() {
		return lsmod;
	}

	public void setLsmod(String lsmod) {
		this.lsmod = lsmod;
	}

	public String getPasswdInfo() {
		return passwdInfo;
	}

	public void setPasswdInfo(String passwdInfo) {
		this.passwdInfo = passwdInfo;
	}

	public String getCrontab() {
		return crontab;
	}

	public void setCrontab(String crontab) {
		this.crontab = crontab;
	}

	public String getPromisc() {
		return promisc;
	}

	public void setPromisc(String promisc) {
		this.promisc = promisc;
	}

	public String getRpcinfo() {
		return rpcinfo;
	}

	public void setRpcinfo(String rpcinfo) {
		this.rpcinfo = rpcinfo;
	}
	
   
}