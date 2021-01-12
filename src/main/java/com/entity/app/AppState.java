package com.entity.app;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.util.DateUtil;

/**
 *
 * @ClassName:AppState.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: app状态监控
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class AppState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2913111613773445949L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 应用信息ID
	 */
	private String appInfoId;
	

	/**
	 * %CPU
	 */
    private String cpuPer;

    /**
	 * %MEM
	 */
    private String memPer;
    
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

	public String getCpuPer() {
		return cpuPer;
	}

	public void setCpuPer(String cpuPer) {
		this.cpuPer = cpuPer;
	}

	
	public String getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
	}

	public String getMemPer() {
		return memPer;
	}

	public void setMemPer(String memPer) {
		this.memPer = memPer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDateStr() {
		String str = DateUtil.getDateTimeString(createTime);
		if(!StringUtils.isEmpty(str)&&str.length()>16){
			return str.substring(5);
		}
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

   
}