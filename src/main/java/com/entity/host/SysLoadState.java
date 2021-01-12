package com.entity.host;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @ClassName:SysLoadState.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: uptime查看系统负载状态
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class SysLoadState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4863071148000213553L;

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
	 * 1分钟之前到现在的负载
	 */
    private String oneLoad;

    /**
	 *5分钟之前到现在的负载
	 */
    private String fiveLoad;
    
    /**
   	 *15分钟之前到现在的负载
   	 */
    private String fifteenLoad;
    
    /**
     * 登录用户数量
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getOneLoad() {
		return oneLoad;
	}

	public void setOneLoad(String oneLoad) {
		this.oneLoad = oneLoad;
	}

	public String getFiveLoad() {
		return fiveLoad;
	}

	public void setFiveLoad(String fiveLoad) {
		this.fiveLoad = fiveLoad;
	}

	public String getFifteenLoad() {
		return fifteenLoad;
	}

	public void setFifteenLoad(String fifteenLoad) {
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