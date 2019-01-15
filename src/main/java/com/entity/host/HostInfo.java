package com.entity.host;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @ClassName:HostInfo.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: host的IP密码等信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class HostInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3875927332935900938L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * host名称
	 */
    private String hostname;

    /**
	 * 用户
	 */
    private String username;
    
    /**
	 * 密码
	 */
    private String password;
    
    
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

    
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}