/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.util;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.heshidai.gold.console.common.entity.AjaxStatu;

/**
 * 功能：返回数据工具类
 *
 * @version 2016年12月26日下午6:02:00
 * @author baocheng.ren
 */
public class ResultUtil {
    
    /**
     * 功能：返回界面方法
     *
     * @version 2017年1月4日下午2:32:40
     * @author baocheng.ren
     * @param datas 数据
     * @param count 数量
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String getJsonString(List datas, Long count) {
        JSONObject obj = new JSONObject();
        obj.put("Rows", datas);
        obj.put("Total", count == null ? 0 : count);
        return obj.toString();
    }
    
    @SuppressWarnings("rawtypes")
    public static String getJsonString(List datas, Long count, BigDecimal d1, BigDecimal d2,BigDecimal d3, BigDecimal d4) {
        JSONObject obj = new JSONObject();
        obj.put("Rows", datas);
        obj.put("Total", count == null ? 0 : count);
        obj.put("totalAmount", d1);
        obj.put("useAmount", d2);
        obj.put("goldTotalAmount", d3);
        obj.put("goldUseAmount", d4);
        return obj.toString();
    }
    
    /**
     * 功能：操作成功，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @return String
     */
    public static String successJson() {
        JSONObject obj = new JSONObject();
        obj.put("code", AjaxStatu.SUCCESS.getValue());
        obj.put("msg", AjaxStatu.SUCCESS.getMsg());
        return obj.toString();
    }
    
    /**
     * 功能：操作成功，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @param msg 成功信息
     * @return String
     */
    public static String successJson(String msg) {
        JSONObject obj = new JSONObject();
        obj.put("code", AjaxStatu.SUCCESS.getValue());
        obj.put("msg", msg);
        return obj.toString();
    }
    
    /**
     * 功能：操作成功，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @param msg 成功信息
     * @return String
     */
    public static String successJson(String msg, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("code", AjaxStatu.SUCCESS.getValue());
        obj.put("msg", msg);
        obj.put("data", data);
        return obj.toString();
    }
    
    
    /**
     * 功能：操作失败，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @param msg 错误信息
     * @return String
     */
    public static String failJson() {
        JSONObject obj = new JSONObject();
        obj.put("code", AjaxStatu.SERVER_ERROR.getValue());
        obj.put("msg", AjaxStatu.SERVER_ERROR.getMsg());
        return obj.toString();
    }
    
    /**
     * 功能：操作失败，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @param msg 错误信息
     * @return String
     */
    public static String failJson(String msg) {
        JSONObject obj = new JSONObject();
        obj.put("code", AjaxStatu.SERVER_ERROR.getValue());
        obj.put("msg", msg);
        return obj.toString();
    }
    
    /**
     * 功能：操作失败，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author baocheng.ren
     * @param code 错误编码
     * @param msg 错误信息
     * @return String
     */
    public static String failJson(String code, String msg) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("msg", msg);
        return obj.toString();
    }
    
    
    /**
     * 功能：操作失败，返回json格式的数据
     *
     * @version 2017年1月17日下午4:33:17
     * @author tangys
     * @param code 错误编码
     * @param msg 错误信息
     * @return String
     */
    public static String failJson(String code, String msg, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("msg", msg);
        obj.put("data", data);
        return obj.toString();
    }
}
