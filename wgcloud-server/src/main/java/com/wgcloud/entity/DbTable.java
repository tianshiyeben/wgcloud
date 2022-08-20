package com.wgcloud.entity;

import java.sql.Timestamp;

/**
 * @version v2.3
 * @ClassName:DbTable.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 检查系统入侵信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DbTable extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * 数据源
     */
    private String dbInfoId;


    /**
     * 表名称
     */
    private String tableName;

    /**
     * where条件值
     */
    private String whereVal;

    /**
     * 表别名
     */
    private String remark;

    private Long tableCount;

    private Long value;

    private String dateStr;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWhereVal() {
        return whereVal;
    }

    public void setWhereVal(String whereVal) {
        this.whereVal = whereVal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTableCount() {
        return tableCount;
    }

    public void setTableCount(Long tableCount) {
        this.tableCount = tableCount;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getValue() {
        return tableCount;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getDbInfoId() {
        return dbInfoId;
    }

    public void setDbInfoId(String dbInfoId) {
        this.dbInfoId = dbInfoId;
    }
}