package com.entity.host;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @ClassName:SystemInfo.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看系统信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class SystemInfo implements Serializable{
	

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
	 * 系统版本信息
	 */
    private String version;

    /**
	 *系统版本详细信息
	 */
    private String versionDetail;
    
    /**
	 * 物理CPU个数
	 */
    private String cpuNum;
    
    /**
   	 * 每个物理CPU中core的个数(即核数)
   	 */
    private String cpuCoreNum;
    
    /**
     * 系统已经运行了多少天
     */
    private String yxDays;
    
    /**
     * CPU型号信息
     */
    private String cpuXh;
    
    
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

    
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionDetail() {
		return versionDetail;
	}

	public void setVersionDetail(String versionDetail) {
		this.versionDetail = versionDetail;
	}

	public String getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	public String getCpuCoreNum() {
		return cpuCoreNum;
	}

	public void setCpuCoreNum(String cpuCoreNum) {
		this.cpuCoreNum = cpuCoreNum;
	}

	
	public String getYxDays() {
		return yxDays;
	}

	public void setYxDays(String yxDays) {
		this.yxDays = yxDays;
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

	public String getCpuXh() {
		return cpuXh;
	}

	public void setCpuXh(String cpuXh) {
		this.cpuXh = cpuXh;
	}
	
	

   
}