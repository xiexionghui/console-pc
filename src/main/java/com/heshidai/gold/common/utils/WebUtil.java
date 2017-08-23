/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web工具类
 * 
 * @version 2016-5-3下午8:20:13
 * @author xiaoyun.zeng
 */
public class WebUtil {
    /**
     * 从request中获取客户端IP地址
     * 
     * @author xiaoyun.zeng
     * @date 2015-4-15下午1:48:38
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("http_remote_user_ip");
        String localIP = "127.0.0.1";
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 判断请求是否Ajax请求
     * @author xiaoyun.zeng
     * @date 2015-4-27上午11:00:42
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String head = request.getHeader("x-requested-with");
        return head != null && head.equalsIgnoreCase("XMLHttpRequest");
    }
    
    /**
     * 获取客户端IP地址（仅支持SpringMvc）
     * 
     * @version 2016-5-3下午8:22:53
     * @author xiaoyun.zeng
     * @return
     */
    public static String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return getClientIp(request);
    }
}
