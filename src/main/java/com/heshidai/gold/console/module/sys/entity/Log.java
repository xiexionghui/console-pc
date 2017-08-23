package com.heshidai.gold.console.module.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能：日志类
 *
 * @version 2016年12月26日下午2:56:23
 * @author baocheng.ren
 */
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id; // 日志编号
    private String type; // 日志类型（1：接入日志；2：错误日志）
    private int createBy; // 创建者
    private Date createDate; // 日志创建时间
    private String remoteAddr; // 操作用户的IP地址
    private String requestUri; // 操作的URI
    private String method; // 操作的方式
    private String params; // 操作提交的数据
    private String userAgent; // 操作用户代理信息
    private String exception; // 异常信息
    private long executeTime; // 执行时间
    private String name; // 用户姓名
    private String username; // 用户名
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getExecuteTime() {
        return executeTime;
    }
    
    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }
    
    public int getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getRemoteAddr() {
        return remoteAddr;
    }
    
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    public String getRequestUri() {
        return requestUri;
    }
    
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getParams() {
        return params;
    }
    
    public void setParams(String params) {
        this.params = params;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    public String getException() {
        return exception;
    }
    
    public void setException(String exception) {
        this.exception = exception;
    }
    
}
