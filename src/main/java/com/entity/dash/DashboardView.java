package com.entity.dash;

import java.io.Serializable;

/**
 *
 * @ClassName:DashboardView.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 主面板概要信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class DashboardView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1262528746414406709L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * host名称
	 */
    private String hostname;

    /**
	 *系统版本信息
	 */
    private String version;
    
    /**
   	 *系统已经运行了多少天
   	 */
    private String yxDays;
    
    /**
	 * 内存已使用百分比
	 */
    private double memPer;
    

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getYxDays() {
		return yxDays;
	}

	public void setYxDays(String yxDays) {
		this.yxDays = yxDays;
	}

	public double getMemPer() {
		return memPer;
	}

	public void setMemPer(double memPer) {
		this.memPer = memPer;
	}

	
    
}