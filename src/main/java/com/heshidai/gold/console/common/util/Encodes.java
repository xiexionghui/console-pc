/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.heshidai.gold.console.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 封装各种格式的编码解码工具类. 1.Commons-Codec的 hex/base64 编码 2.自制的base62 编码 3.Commons-Lang的xml/html escape 4.JDK提供的URLEncoder
 * 
 * @author calvin
 * @version 2013-01-15
 */
public class Encodes {
    
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final int HEXADECIMAL_255 = 0xFF;
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    
    /**
     * 功能：Hex编码
     *
     * @version 2017年1月4日下午2:04:20
     * @author baocheng.ren
     * @param input 输入
     * @return String
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }
    
    /**
     * 功能：Hex解码
     *
     * @version 2017年1月4日下午2:04:57
     * @author baocheng.ren
     * @param input 输入
     * @return byte[]
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        }
        catch (DecoderException e) {
            throw Exceptions.unchecked(e);
        }
    }
    
    /**
     * 功能：Base64编码
     *
     * @version 2017年1月4日下午2:05:16
     * @author baocheng.ren
     * @param input 输入
     * @return String
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }
    
    /**
     * 功能：Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548)
     *
     * @version 2017年1月4日下午2:05:31
     * @author baocheng.ren
     * @param input 输入
     * @return String
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }
    
    /**
     * 功能：Base64解码
     *
     * @version 2017年1月4日下午2:05:48
     * @author baocheng.ren
     * @param input 输入
     * @return byte[]
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }
    
    /**
     * 功能：Base62编码
     *
     * @version 2017年1月4日下午2:06:05
     * @author baocheng.ren
     * @param input 输入
     * @return String
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & HEXADECIMAL_255) % BASE62.length)];
        }
        return new String(chars);
    }
    
    /**
     * 功能：Html转码
     *
     * @version 2017年1月4日下午2:06:22
     * @author baocheng.ren
     * @param html 输入
     * @return String
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }
    
    /**
     * 功能：Html解码
     *
     * @version 2017年1月4日下午2:06:47
     * @author baocheng.ren
     * @param htmlEscaped 输入
     * @return String
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }
    
    /**
     * 功能：Xml转码
     *
     * @version 2017年1月4日下午2:07:11
     * @author baocheng.ren
     * @param xml 输入
     * @return String
     */
    @SuppressWarnings("deprecation")
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }
    
    /**
     * 功能：Xml解码
     *
     * @version 2017年1月4日下午2:07:55
     * @author baocheng.ren
     * @param xmlEscaped 输入
     * @return String
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }
    
    /**
     * 功能：URL 编码, Encode默认为UTF-8
     *
     * @version 2017年1月4日下午2:08:13
     * @author baocheng.ren
     * @param part 输入
     * @return String
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        }
        catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }
    
    /**
     * 功能：URL 解码, Encode默认为UTF-8
     *
     * @version 2017年1月4日下午2:08:30
     * @author baocheng.ren
     * @param part 输入
     * @return String
     */
    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        }
        catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }
}
