/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.heshidai.gold.console.common.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Http与Servlet工具类.
 * 
 * @author calvin/thinkgem
 * @version 2014-8-19
 */
public class Servlets {
    // 静态文件后缀
    private static final String[] STATIC_FILES = StringUtils.split(Servlets.WEB_STATIC_FILE, ",");
    
    private static final String WEB_STATIC_FILE = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,"
                                                + ".psd,.htc,.htm,.html,.crx,.xpi,.exe,.ipa,.apk";
    
    private static final String URL_SUFFIX = ".html";
    
    /**
     * 功能：获取request
     *
     * @version 2017年1月4日下午3:07:13
     * @author baocheng.ren
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 功能：判断访问URI是否是静态文件请求
     *
     * @version 2017年1月4日下午3:07:31
     * @author baocheng.ren
     * @param uri uri
     * @return boolean
     */
    public static boolean isStaticFile(String uri) {
        if (STATIC_FILES == null) {
            try {
                throw new Exception(
                        "检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                                + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,"
                                + ".ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        // if ((StringUtils.startsWith(uri, "/static/") || StringUtils.endsWithAny(uri, sfs))
        // && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")){
        // return true;
        // }
        if (StringUtils.endsWithAny(uri, STATIC_FILES) && !StringUtils.endsWithAny(uri, URL_SUFFIX)
                && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")) {
            return true;
        }
        return false;
    }
}
