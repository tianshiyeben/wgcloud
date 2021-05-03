package com.wgcloud.dto;

import com.wgcloud.entity.BaseEntity;

/**
 * @version v2.3
 * @ClassName:ChartInfo.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: app端口信息
 * @Copyright: 2017-2021 wgcloud. All rights reserved.
 */
public class ChartInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * host名称
     */
    private String item;

    /**
     * 应用进程ID
     */
    private Integer count;

    /**
     * 应用进程名称
     */
    private Double percent;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}