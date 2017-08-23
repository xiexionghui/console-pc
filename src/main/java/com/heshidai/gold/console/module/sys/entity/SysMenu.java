package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;

/**
 * 功能：菜单权限类
 *
 * @version 2016年12月26日下午2:50:29
 * @author baocheng.ren
 */
public class SysMenu implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 父id：0-顶级菜单
     */
    private String parentId;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 链接地址
     */
    private String href;
    
    /**
     * 目标地址
     */
    private String target;
    
    /**
     * 图标地址
     */
    private String icon;
    
    /**
     * 排序
     */
    private String sort;
    
    /**
     * 权限名
     */
    private String permission;
    
    /**
     * 状态：0-可用，1-禁用
     */
    private String status;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 更新时间
     */
    private String updateTime;
    
    /**
     * 角色id
     */
    private String roleId;
    
    /**
     * 1-具有此权限，0-不具有此权限
     */
    private String flag;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public String getTarget() {
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getSort() {
        return sort;
    }
    
    public void setSort(String sort) {
        this.sort = sort;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getFlag() {
        return flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
}