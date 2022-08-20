package com.wgcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:SystemInfo.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 查看系统信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class SystemInfo extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 系统版本信息
     */
    private String version;

    /**
     * 系统版本详细信息
     */
    private String versionDetail;

    /**
     * 内存使用率
     */
    private Double memPer;

    /**
     * core的个数(即核数)
     */
    private String cpuCoreNum;

    /**
     * cpu使用率
     */
    private Double cpuPer;

    /**
     * CPU型号信息
     */
    private String cpuXh;


    /**
     * 主机状态，1正常，2下线
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    //磁盘总使用率%
    private Double diskPer;

    /**
     * 主机备注
     */
    private String remark;


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


    public String getCpuCoreNum() {
        return cpuCoreNum;
    }

    public void setCpuCoreNum(String cpuCoreNum) {
        this.cpuCoreNum = cpuCoreNum;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Double getMemPer() {
        return memPer;
    }

    public void setMemPer(Double memPer) {
        this.memPer = memPer;
    }

    public Double getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(Double cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getDiskPer() {
        return diskPer;
    }

    public void setDiskPer(Double diskPer) {
        this.diskPer = diskPer;
    }
}