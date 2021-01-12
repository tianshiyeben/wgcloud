package com.entity.host;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @ClassName:MemState.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看内存使用情况
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MemState implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1412473355088780549L;

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
	 * 总计内存，M
	 */
    private String total;

    /**
	 *已使用多少，M
	 */
    private String used;
    
    /**
	 * 未使用，M
	 */
    private String free;
    
    /**
     * 已使用百分比%
     */
    private String usePer;
    
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

    
    
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
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

	public String getUsePer() {
		return usePer;
	}

	public void setUsePer(String usePer) {
		this.usePer = usePer;
	}

	
   
}