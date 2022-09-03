package com.wgcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version V2.3
 * @ClassName:DeskState.java
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: 查看磁盘大小使用信息
 * @Copyright: 2017-2022 www.wgstart.com. All rights reserved.
 */
public class DeskState extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 盘符类型
     */
    private String fileSystem;

    /**
     * 分区大小
     */
    private String size;

    /**
     * 已使用
     */
    private String used;

    /**
     * 可用
     */
    private String avail;

    /**
     * 已使用百分比
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


    public String getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(String fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getUsePer() {
        return usePer;
    }

    public void setUsePer(String usePer) {
        this.usePer = usePer;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(5);
        }
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


}