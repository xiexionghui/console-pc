/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;

/**
 * 功能：定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 *
 * @version 2016年9月22日上午9:11:10
 * @author baocheng.ren
 */
public class ShiroUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户id
     */
    private String id;
    
    /**
     * 组织机构id
     */
    private String organId;
    
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 加密后的密码
     */
    private String password;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrganId() {
        return organId;
    }
    
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName + "（" + realName + "）";
    }
    
}