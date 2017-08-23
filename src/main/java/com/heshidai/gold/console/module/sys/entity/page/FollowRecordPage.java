/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.entity.page;

import java.io.Serializable;

import com.heshidai.gold.console.common.entity.Page;

/**
 * 功能：跟进记录分页类
 *
 * @version 2017年1月19日上午10:22:26
 * @author baocheng.ren
 */
public class FollowRecordPage extends Page implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 客户id
     */
    private String customerId;
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
}
