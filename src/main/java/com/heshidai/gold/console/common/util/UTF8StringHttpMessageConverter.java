/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

/**
 * 将String类型数据编码转为UTF8编码
 * 
 * @version 2016年9月6日下午3:55:49
 * @author wenbin.zhu
 */

public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {
    
    /**
     * UTF-8
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    private final List<Charset> availableCharsets;
    
    private boolean writeAcceptCharset = true;
    
    public UTF8StringHttpMessageConverter() {
        super(new MediaType("text", "plain", DEFAULT_CHARSET), MediaType.ALL);
        this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());
    }
    
    /**
     * 功能：设置
     *
     * @version 2017年1月4日下午3:46:18
     * @author baocheng.ren
     * @param writeAcceptCharset writeAcceptCharset
     */
    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }
    
    @Override
    protected String readInternal(@SuppressWarnings("rawtypes") Class clazz, HttpInputMessage inputMessage)
            throws IOException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
    }
    
    @Override
    protected Long getContentLength(String s, MediaType contentType) {
        Charset charset = getContentTypeCharset(contentType);
        try {
            return (long) s.getBytes(charset.name()).length;
        }
        catch (UnsupportedEncodingException ex) {
            // should not occur
            throw new InternalError(ex.getMessage());
        }
    }
    
    @Override
    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        if (writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }
    
    /**
     * Return the list of supported {@link Charset}.
     *
     * <p>
     * By default, returns {@link Charset#availableCharsets()}. Can be overridden in subclasses.
     *
     * @return the list of accepted charsets
     */
    protected List<Charset> getAcceptedCharsets() {
        return this.availableCharsets;
    }
    
    /**
     * 功能：获取
     *
     * @version 2017年1月4日下午3:46:42
     * @author baocheng.ren
     * @param contentType contentType
     * @return Charset
     */
    @SuppressWarnings("deprecation")
    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            return contentType.getCharSet();
        }
        else {
            return DEFAULT_CHARSET;
        }
    }
}
