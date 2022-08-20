package com.wgcloud.dto;

import com.wgcloud.entity.BaseEntity;

/**
 * @version v2.3
 * @ClassName:ChartInfo.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 图表dto信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class ChartInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * 名称
     */
    private String item;

    private Integer count;

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