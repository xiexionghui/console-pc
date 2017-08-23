/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http工具类
 * @version 2017-1-7上午11:38:17
 * @author tangys
 */

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static String CHARSET_UTF8 = "utf-8";

    public static String post(String url, Map<String, String> parametersMap) {
        try {
            HttpPost request = new HttpPost(url);

            // setparameters
            List<NameValuePair> parametersLst = new ArrayList<>();
            for (Map.Entry<String, String> e : parametersMap.entrySet()) {
                parametersLst.add(new BasicNameValuePair(e.getKey(), e.getValue()));
            }
            HttpEntity reqEntity = new UrlEncodedFormEntity(parametersLst, CHARSET_UTF8);
            request.setEntity(reqEntity);

            // setHeaders
            TreeMap<String, String> headersMap = new TreeMap<String, String>();
            /*headersMap.put("deviceId", "httpclient-test");
            headersMap.put("sysName", "41");*/

            for (Map.Entry<String, String> e : headersMap.entrySet()) {
                request.setHeader(e.getKey(), e.getValue());
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse res = httpclient.execute(request);
            String htmlStr = EntityUtils.toString(res.getEntity(), CHARSET_UTF8);
            return htmlStr;
        } catch (ClientProtocolException e) {
            logger.error("请求失败",e);
        } catch (IOException e) {
            logger.error("请求失败",e);
        }
        return null;
    }
    
    
    public static String get(String url, Map<String, String> parametersMap) {
        try {
            
            // setparameters
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> e : parametersMap.entrySet()) {
                sb.append("&").append(e.getKey()).append("=").append(e.getValue());
            }
            String parameterStr = sb.toString().replaceFirst("&", "?");
            HttpGet request = new HttpGet(url+parameterStr);
            System.out.println("url:"+request.getURI());
            // setHeaders
            TreeMap<String, String> headersMap = new TreeMap<String, String>();
            /*headersMap.put("deviceId", "httpclient-test");
            headersMap.put("sysName", "41");*/

            for (Map.Entry<String, String> e : headersMap.entrySet()) {
                request.setHeader(e.getKey(), e.getValue());
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse res = httpclient.execute(request);
            String htmlStr = EntityUtils.toString(res.getEntity(), CHARSET_UTF8);
            return htmlStr;
        } catch (ClientProtocolException e) {
            logger.error("请求失败",e);
        } catch (IOException e) {
            logger.error("请求失败",e);
        }
        return null;
    }
}
