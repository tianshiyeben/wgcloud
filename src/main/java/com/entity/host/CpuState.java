package com.entity.host;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @ClassName:CpuState.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 查看CPU使用情况
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class CpuState implements Serializable{
	
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
	 * 用户态的CPU时间（%）
	 */
    private String user;

    /**
	 * 系统（内核）时间（%）
	 */
    private String sys;
    
    /**
	 * 空闲时间（idle）（%）
	 */
    private String idle;
    
    /**
   	 * IO等待时间（wait）（%）
   	 */
    private String iowait;
    
    /**
   	 * 硬中断时间（%）
   	 */
    private String irq;
    
    /**
     * 软中断时间（%）
     */
    private String soft;
    
    /**
     * 添加时间
     * MM-dd hh:mm:ss
     */
    private String dateStr;
    
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getIdle() {
		return idle;
	}

	public void setIdle(String idle) {
		this.idle = idle;
	}

	public String getIowait() {
		return iowait;
	}

	public void setIowait(String iowait) {
		this.iowait = iowait;
	}

	public String getIrq() {
		return irq;
	}

	public void setIrq(String irq) {
		this.irq = irq;
	}

	public String getSoft() {
		return soft;
	}

	public void setSoft(String soft) {
		this.soft = soft;
	}

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDateStr() {
		if(!StringUtils.isEmpty(dateStr)&&dateStr.length()>16){
			return dateStr.substring(5);
		}
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
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

    
   
}