package com.entity.host;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @ClassName:DiskIoState.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看磁盘IO使用情况
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class DiskIoState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284741180883299533L;

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
	 * 每秒读次数
	 */
    private String rs;

    /**
	 * 每秒写次数
	 */
    private String ws;
    
    /**
	 *每秒读数据量（千字节）
	 */
    private String rkBS;
    
    /**
   	 * 每秒写数据量（千字节）
   	 */
    private String wkBS;
    
    /**
   	 * IO操作的平均等待时间，单位是毫秒。这是应用程序在和磁盘交互时，需要消耗的时间，包括IO等待和实际操作的耗时。如果这个数值过大，可能是硬件设备遇到了瓶颈或者出现故障
   	 */
    private String await;
    
    /**
     * 向设备发出的请求平均数量。如果这个数值大于1，可能是硬件设备已经饱和（部分前端硬件设备支持并行写入）
     */
    private String avgquSz;
    
    /**
     * 设备利用率。这个数值表示设备的繁忙程度，经验值是如果超过60，可能会影响IO性能（可以参照IO操作平均等待时间）。如果到达100%，说明硬件设备已经饱和
     */
    private String util;
    
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


	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getWs() {
		return ws;
	}

	public void setWs(String ws) {
		this.ws = ws;
	}

	public String getRkBS() {
		return rkBS;
	}

	public void setRkBS(String rkBS) {
		this.rkBS = rkBS;
	}

	public String getWkBS() {
		return wkBS;
	}

	public void setWkBS(String wkBS) {
		this.wkBS = wkBS;
	}

	public String getAwait() {
		return await;
	}

	public void setAwait(String await) {
		this.await = await;
	}

	public String getAvgquSz() {
		return avgquSz;
	}

	public void setAvgquSz(String avgquSz) {
		this.avgquSz = avgquSz;
	}

	public String getUtil() {
		return util;
	}

	public void setUtil(String util) {
		this.util = util;
	}

    
   
}