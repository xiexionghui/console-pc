/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.entity;


/**
 * ajax返回结果消息体
 * 
 * @version 2017年1月5日下午5:10:47
 * @author dx.love
 */
public class AjaxRs extends EntityVo {
    private static final long serialVersionUID = 1L;
    /**
     * 回调返回码 200表示正确
     */
    private String resultCode;
    /**
     * 回调返回描述
     */
    private String resultMsg;
    /**
     * 内容
     */
    private Object content;
    
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getResultMsg() {
        return resultMsg;
    }
    
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    
}
