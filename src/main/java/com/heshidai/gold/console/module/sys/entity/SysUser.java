package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;

import com.heshidai.gold.console.common.entity.EntityVo;

/**
 * 功能：用户信息
 *
 * @version 2016年12月26日下午2:45:44
 * @author baocheng.ren
 */
public class SysUser extends EntityVo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键id
     */
    private String id;
    
    /**
     * 机构ID
     */
    private String organId;
    
    /**
     * 登录名
     */
    private String loginName;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 界面密码
     */
    private String passwordUser;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 生日
     */
    private String birthday;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 办公电话
     */
    private String officeTel;
    
    /**
     * 家庭电话
     */
    private String homeTel;
    
    /**
     * 用户状态:正常，限制登录，离职
     */
    private String status;
    
    /**
     * 最后登陆IP
     */
    private String loginIp;
    
    /**
     * 最后登陆时间
     */
    private String loginTime;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 修改时间
     */
    private String updateTime;
    
    /**
     * 部门名称
     */
    private String organName;
    
    /**
     * 离职状态： 0-在职，1-离职
     */
    private String dimissionStatus;
    
    /**
     * 离职时间
     */
    private String dimissionTime;
    
    /**
     * 手机号码
     */
    private String mobilePhone;
    
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getOfficeTel() {
        return officeTel;
    }
    
    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }
    
    public String getHomeTel() {
        return homeTel;
    }
    
    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getLoginIp() {
        return loginIp;
    }
    
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    
    public String getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
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

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getDimissionStatus() {
        return dimissionStatus;
    }

    public void setDimissionStatus(String dimissionStatus) {
        this.dimissionStatus = dimissionStatus;
    }

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
