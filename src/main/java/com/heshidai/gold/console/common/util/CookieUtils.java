/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.heshidai.gold.console.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * 
 * @author ThinkGem
 * @version 2013-01-15
 */
public class CookieUtils {
    
    /**
     * 时间：86400 = 60*60*24
     */
    private static final int TIMES = 86400;
    
    /**
     * 功能：设置 Cookie（生成时间为1天）
     *
     * @version 2017年1月4日上午9:48:05
     * @author baocheng.ren
     * @param response response
     * @param name 名称
     * @param value 值
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, TIMES);
    }
    
    /**
     * 功能：设置 Cookie
     *
     * @version 2017年1月4日上午9:50:48
     * @author baocheng.ren
     * @param response response
     * @param name 名称
     * @param value 值
     * @param path 路径
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        setCookie(response, name, value, path, TIMES);
    }
    
    /**
     * 功能：设置 Cookie
     *
     * @version 2017年1月4日上午9:51:39
     * @author baocheng.ren
     * @param response response
     * @param name 名称
     * @param value 值
     * @param maxAge 生存时间（单位秒）
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }
    
    /**
     * 功能：设置 Cookie
     *
     * @version 2017年1月4日上午9:52:33
     * @author baocheng.ren
     * @param response response
     * @param name 名称
     * @param value 值
     * @param path 路径
     * @param maxAge 生存时间（单位秒）
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        try {
            cookie.setValue(URLEncoder.encode(value, "utf-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }
    
    /**
     * 功能：获得指定Cookie的值
     *
     * @version 2017年1月4日上午9:53:25
     * @author baocheng.ren
     * @param request request
     * @param name 名称
     * @return String
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, null, name, false);
    }
    
    /**
     * 功能：获得指定Cookie的值，并删除
     *
     * @version 2017年1月4日上午9:53:51
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @param name 名称
     * @return String
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }
    
    /**
     * 获得指定Cookie的值
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param name 名字
     * @param isRemove 是否移除
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name,
            boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    }
                    catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        return value;
    }
}
