/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.entity.page;

import java.io.Serializable;

import com.heshidai.gold.console.common.entity.Page;

 /**
  * 功能：用户信息分页类
  *
  * @version 2016年12月26日下午5:46:40
  * @author baocheng.ren
  */
public class SysUserPage extends Page implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 登录名搜索
     */
    private String loginName;
    
    /**
     * 真实姓名搜索
     */
    private String realName;
    
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
    
}
