package com.entity.log;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @ClassName:LogInfo.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 日志信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class LogInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1565538727002722890L;

	/**
	 * 主键
	 */
	private String id;
	
	
	/**
	 * 用户帐号
	 */
	private String account;
	
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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