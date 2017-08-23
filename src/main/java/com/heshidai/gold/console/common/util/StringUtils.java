/******************************************************************************
 * Copyright (C) 2014 ShenZhen HeShiDai andy.zl Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.heshidai.gold.console.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 功能：字符串处理工具类（用于一些特殊的场景对应的自定义方法工具类。其他一般情况调用apache common下的工具类）
 *
 * @version 2017年1月4日下午2:47:00
 * @author baocheng.ren
 */
public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {
    
    private static final String CHARSET_NAME = "UTF-8";
    
    private static final int NUMBER_3 = 3;
    
    /**
     * 功能：取对象对应的字符串值，为null转换为空引号
     *
     * @version 2017年1月4日下午2:47:34
     * @author baocheng.ren
     * @param value 对象
     * @return String
     */
    public static String valueOfReplaceNull(Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }
    
    /**
     * 功能：获得用户远程地址
     *
     * @version 2017年1月4日下午2:48:01
     * @author baocheng.ren
     * @param request request
     * @return String
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }
        else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
    
    /**
     * 功能：缩略字符串（不区分中英文字符）
     *
     * @version 2017年1月4日下午2:48:19
     * @author baocheng.ren
     * @param str 目标字符串
     * @param length 截取长度
     * @return String
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - NUMBER_3) {
                    sb.append(c);
                }
                else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 功能：替换掉HTML标签方法
     *
     * @version 2017年1月4日下午2:48:44
     * @author baocheng.ren
     * @param html html
     * @return String
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }
    
    /**
     * 功能：转换为字节数组
     *
     * @version 2017年1月4日下午2:48:59
     * @author baocheng.ren
     * @param str 字符串
     * @return byte[]
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            }
            catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
