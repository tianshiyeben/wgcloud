package com.wgcloud.dto;

import com.wgcloud.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:NetIoState.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 网络设备的吞吐率
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class NetIoStateDto extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -8314012397341825158L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 每秒钟接收的数据包,rxpck/s
     */
    private Integer rxpck;

    /**
     * 每秒钟发送的数据包,txpck/s
     */
    private Integer txpck;


    /**
     * 每秒钟接收的KB数,rxkB/s
     */
    private Integer rxbyt;


    /**
     * 每秒钟发送的KB数,txkB/s
     */
    private Integer txbyt;


    /**
     * 添加时间
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getRxpck() {
        return rxpck;
    }

    public void setRxpck(Integer rxpck) {
        this.rxpck = rxpck;
    }

    public Integer getTxpck() {
        return txpck;
    }

    public void setTxpck(Integer txpck) {
        this.txpck = txpck;
    }

    public Integer getRxbyt() {
        return rxbyt;
    }

    public void setRxbyt(Integer rxbyt) {
        this.rxbyt = rxbyt;
    }

    public Integer getTxbyt() {
        return txbyt;
    }

    public void setTxbyt(Integer txbyt) {
        this.txbyt = txbyt;
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