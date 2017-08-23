/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.entity.page;

import java.io.Serializable;

import com.heshidai.gold.console.common.entity.Page;

/**
 * 功能：角色类
 *
 * @version 2016年12月9日上午9:10:56
 * @author baocheng.ren
 */
public class RolePage extends Page implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 项目id
     */
    private String itemId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
