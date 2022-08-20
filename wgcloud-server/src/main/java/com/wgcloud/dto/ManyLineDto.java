package com.wgcloud.dto;

import com.wgcloud.entity.BaseEntity;

/**
 * @version v2.3
 * @ClassName:ManyLineDto.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 表单提交返回信息
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class ManyLineDto extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    private String one;

    private String oneAlias;

    private String two;

    private String twoAlias;

    private String three;

    private String threeAlias;

    private double maxval;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getOneAlias() {
        return oneAlias;
    }

    public void setOneAlias(String oneAlias) {
        this.oneAlias = oneAlias;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getTwoAlias() {
        return twoAlias;
    }

    public void setTwoAlias(String twoAlias) {
        this.twoAlias = twoAlias;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getThreeAlias() {
        return threeAlias;
    }

    public void setThreeAlias(String threeAlias) {
        this.threeAlias = threeAlias;
    }

    public double getMaxval() {
        return maxval;
    }

    public void setMaxval(double maxval) {
        this.maxval = maxval;
    }

    public void setSysLoad(int index, String key, String alias) {
        if (0 == index) {
            this.one = key;
            this.oneAlias = alias;
        }
        if (1 == index) {
            this.two = key;
            this.twoAlias = alias;
        }
        if (2 == index) {
            this.three = key;
            this.threeAlias = alias;
        }


    }
}