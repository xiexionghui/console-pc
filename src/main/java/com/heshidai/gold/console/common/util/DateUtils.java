/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.heshidai.gold.console.common.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    
    /**
     * 一天毫秒数：86400000 = 1000*60*60*24
     */
    private static final long ONE_DAY_MILLISECOND = 86400000;
    
    /**
     * 一小时毫秒数：3600000 = 1000*60*60
     */
    private static final long ONE_HOUR_MILLISECOND = 3600000;
    
    /**
     * 一分钟毫秒数：60000 = 1000*60
     */
    private static final long ONE_MINUTES_MILLISECOND = 60000;
    
    /**
     * 日期模式
     */
    public static String[] PARSE_PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
        "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
        "yyyy.MM.dd HH:mm", "yyyy.MM" };
    
    /**
     * 功能：得到当前日期字符串 格式（yyyy-MM-dd）
     *
     * @version 2017年1月4日下午1:46:26
     * @author baocheng.ren
     * @return String
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }
    
    /**
     * 功能：得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @version 2017年1月4日下午1:46:42
     * @author baocheng.ren
     * @param pattern 匹配
     * @return String
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }
    
    /**
     * 功能：得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @version 2017年1月4日下午1:47:05
     * @author baocheng.ren
     * @param date 日期
     * @param pattern 匹配类型
     * @return String
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        }
        else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }
    
    /**
     * 功能：得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     *
     * @version 2017年1月4日下午1:47:33
     * @author baocheng.ren
     * @param date 日期
     * @return String
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 功能：得到当前时间字符串 格式（HH:mm:ss）
     *
     * @version 2017年1月4日下午1:47:53
     * @author baocheng.ren
     * @return String
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }
    
    /**
     * 功能：得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     *
     * @version 2017年1月4日下午1:48:06
     * @author baocheng.ren
     * @return String
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 功能：得到当前日期和时间字符串 格式（yyyyMMddHHmmss）
     *
     * @version 2017年1月4日下午1:48:06
     * @author baocheng.ren
     * @return String
     */
    public static String getDateTimeyyyyMMddHHmmss() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }
    
    /**
     * 功能：得到当前年份字符串 格式（yyyy）
     *
     * @version 2017年1月4日下午1:48:19
     * @author baocheng.ren
     * @return String
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }
    
    /**
     * 功能：得到当前月份字符串 格式（MM）
     *
     * @version 2017年1月4日下午1:48:34
     * @author baocheng.ren
     * @return String
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }
    
    /**
     * 功能：得到当天字符串 格式（dd）
     *
     * @version 2017年1月4日下午1:48:48
     * @author baocheng.ren
     * @return String
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }
    
    /**
     * 功能：得到当前星期字符串 格式（E）星期几
     *
     * @version 2017年1月4日下午1:48:57
     * @author baocheng.ren
     * @return String
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }
    
    /**
     * 功能：日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", 
     * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     *
     * @version 2017年1月4日下午1:49:17
     * @author baocheng.ren
     * @param str 日期
     * @return Date 日期
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), PARSE_PATTERNS);
        }
        catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 功能：获取过去的天数
     *
     * @version 2017年1月4日下午1:49:47
     * @author baocheng.ren
     * @param date 日期
     * @return long
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / ONE_DAY_MILLISECOND;
    }
    
    /**
     * 功能：获取过去的小时
     *
     * @version 2017年1月4日下午1:50:09
     * @author baocheng.ren
     * @param date 日期
     * @return long
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / ONE_HOUR_MILLISECOND;
    }
    
    /**
     * 功能：获取过去的分钟
     *
     * @version 2017年1月4日下午1:50:27
     * @author baocheng.ren
     * @param date 日期
     * @return long
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / ONE_MINUTES_MILLISECOND;
    }
    
    /**
     * 功能：获取两个日期之间的天数
     *
     * @version 2017年1月4日下午1:51:12
     * @author baocheng.ren
     * @param before 前日期
     * @param after 后日期
     * @return double
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / ONE_DAY_MILLISECOND;
    }
    
    /**
     * 功能：计算当前日期的前一天
     *
     * @version 2017年1月4日下午1:51:37
     * @author baocheng.ren
     * @param date 日期
     * @return Date
     */
    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 功能：计算当前日期的后一天
     *
     * @version 2017年1月4日下午1:51:37
     * @author baocheng.ren
     * @param date 日期
     * @return Date
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }
    
}
