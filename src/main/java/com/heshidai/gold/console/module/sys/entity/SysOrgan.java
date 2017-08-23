/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：组织机构
 *
 * @version 2016年12月27日上午11:10:50
 * @author baocheng.ren
 */
public class SysOrgan implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 部门名称
     */
    private String name;
    
    /**
     * 部门描述
     */
    private String description;
    
    /**
     * 父id：上级部门id
     */
    private String parentId;
    
    /**
     * 上级部门名称
     */
    private String parentName;
    
    /**
     * 排序
     */
    private String sort;
    
    /**
     * 添加时间
     */
    private String createTime;
    
    /**
     * 修改时间
     */
    private String updateTime;
    
    /**
     * 孩子集合
     */
    private List<SysOrgan> children;
    
    /**
     * 元素数量
     */
    private Integer count;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getSort() {
        return sort;
    }
    
    public void setSort(String sort) {
        this.sort = sort;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    public List<SysOrgan> getChildren() {
        return children;
    }
    
    public void setChildren(List<SysOrgan> children) {
        this.children = children;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
}
