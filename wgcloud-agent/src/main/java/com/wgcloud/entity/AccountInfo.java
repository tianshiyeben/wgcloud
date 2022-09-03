package com.wgcloud.entity;

import java.util.Date;

/**
 * @version V2.3
 * @ClassName:AccountInfo.java
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: AccountInfo.java
 * @Copyright: 2017-2022 www.wgstart.com. All rights reserved.
 */
public class AccountInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -6510176051328150406L;

    /**
     * 登录帐号
     */
    private String account;

    /**
     * key标识
     */
    private String accountMd5;

    /**
     * 用户角色，admin管理员，user普通用户,test测试用户
     */
    private String role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 过期时间
     */
    private Date expDate;

    /**
     * 真实姓名
     */
    private String realName;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getAccountMd5() {
        return accountMd5;
    }

    public void setAccountMd5(String accountMd5) {
        this.accountMd5 = accountMd5;
    }


}