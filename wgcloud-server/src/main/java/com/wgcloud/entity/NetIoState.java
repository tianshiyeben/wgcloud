package com.wgcloud.entity;

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
public class NetIoState extends BaseEntity {


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
    private String rxpck;

    /**
     * 每秒钟发送的数据包,txpck/s
     */
    private String txpck;


    /**
     * 每秒钟接收的KB数,rxkB/s
     */
    private String rxbyt;


    /**
     * 每秒钟发送的KB数,txkB/s
     */
    private String txbyt;


    /**
     * 每秒钟接收的压缩数据包,rxcmp/s
     */
    private String rxcmp;


    /**
     * 每秒钟发送的压缩数据包,txcmp/s
     */
    private String txcmp;


    /**
     * 每秒钟接收的多播数据包,rxmcst/s
     */
    private String rxmcst;

    /**
     * 添加时间
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * 创建时间
     */
    private Date createTime;


    public String getRxpck() {
        return rxpck;
    }

    public void setRxpck(String rxpck) {
        this.rxpck = rxpck;
    }

    public String getTxpck() {
        return txpck;
    }

    public void setTxpck(String txpck) {
        this.txpck = txpck;
    }

    public String getRxbyt() {
        return rxbyt;
    }

    public void setRxbyt(String rxbyt) {
        this.rxbyt = rxbyt;
    }

    public String getTxbyt() {
        return txbyt;
    }

    public void setTxbyt(String txbyt) {
        this.txbyt = txbyt;
    }

    public String getRxcmp() {
        return rxcmp;
    }

    public void setRxcmp(String rxcmp) {
        this.rxcmp = rxcmp;
    }

    public String getTxcmp() {
        return txcmp;
    }

    public void setTxcmp(String txcmp) {
        this.txcmp = txcmp;
    }

    public String getRxmcst() {
        return rxmcst;
    }

    public void setRxmcst(String rxmcst) {
        this.rxmcst = rxmcst;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(11);
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