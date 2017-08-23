package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;

import com.heshidai.gold.console.common.entity.EntityVo;

/**
 * 功能：跟进记录类
 *
 * @version 2017年1月19日上午9:53:23
 * @author baocheng.ren
 */
public class FollowRecord extends EntityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长主键
     */
    private String id;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 跟进人id
     */
    private String userId;

    /**
     * 跟进类型：1-充值失败跟进
     */
    private String type;

    /**
     * 跟进时间
     */
    private String followTime;

    /**
     * 下次跟进时间
     */
    private String nextVisitTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 跟进人登录名
     */
    private String loginName;
    
    /**
     * 业务id
     */
    private String bizId;
    
    /**
     * 跟进状态：0=未跟进，1=已跟进
     */
    private String followStatus;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime == null ? null : followTime.trim();
    }

    public String getNextVisitTime() {
        return nextVisitTime;
    }

    public void setNextVisitTime(String nextVisitTime) {
        this.nextVisitTime = nextVisitTime == null ? null : nextVisitTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }
}