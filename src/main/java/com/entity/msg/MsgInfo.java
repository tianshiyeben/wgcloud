package com.entity.msg;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @ClassName:MsgInfo.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 信息发送记录
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class MsgInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2244025815867514416L;


	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 用户Id
	 */
	private String accountId;
	
	
	/**
	 * 用户帐号
	 */
	private String account;
	
	/**
	 * 接受消息的邮箱或者手机号码
	 */
	private String acceptInfo;
	
	/**
	 * 消息正文
	 */
	private String infoContent;
	
	/**
	 * 消息标题
	 */
	private String msgTitle;
    
    
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



	public String getInfoContent() {
		return infoContent;
	}


	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getAcceptInfo() {
		return acceptInfo;
	}


	public void setAcceptInfo(String acceptInfo) {
		this.acceptInfo = acceptInfo;
	}


	public String getMsgTitle() {
		return msgTitle;
	}


	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

}