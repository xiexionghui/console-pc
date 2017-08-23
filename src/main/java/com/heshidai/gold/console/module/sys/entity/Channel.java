package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;
import java.util.List;

import com.heshidai.gold.console.common.entity.EntityVo;

/**
 * 功能：渠道类
 *
 * @version 2017年3月1日上午11:58:20
 * @author baocheng.ren
 */
public class Channel extends EntityVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 自增长主键
     */
    private String id;
    
    /**
     * 父id：0-渠道顶级
     */
    private String parentId;
    
    /**
     * 父渠道名称
     */
    private String parentName;
    
    /**
     * 渠道名称
     */
    private String channelName;
    
    /**
     * 客户ID
     */
    private String customerId;
    
    /**
     * 部门ID
     */
    private String organId;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 版本号
     */
    private String versionNo;
    
    /**
     * 插入时间
     */
    private String createTime;
    
    /**
     * 手机号
     */
    private String mobilePhone;
    
    /**
     * 部门名称
     */
    private String organName;
    
    /**
     * 孩子集合
     */
    private List<Channel> children;
    
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
    
    public String getChannelName() {
        return channelName;
    }
    
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getOrganId() {
        return organId;
    }
    
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getVersionNo() {
        return versionNo;
    }
    
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public String getOrganName() {
        return organName;
    }
    
    public void setOrganName(String organName) {
        this.organName = organName;
    }
    
    public List<Channel> getChildren() {
        return children;
    }
    
    public void setChildren(List<Channel> children) {
        this.children = children;
    }
    
    public String getParentName() {
        return parentName;
    }
    
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
}