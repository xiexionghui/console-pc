/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 反射工具类
 * @version 2017年1月18日下午5:05:38
 * @author dx.love
 */
public class ReflectUtils {
    
    /**
     * 通过字段名从对象或对象的父类中得到字段的值
     * 
     * @param object 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
     * @throws Exception 异常
     * @Author : ll. create at 2016年4月14日 上午9:18:19
     */
    public static Object getValue(Object object, String fieldName) throws Exception {
        if (object == null) {
            return null;
        }
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
            }
            catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        
        return null;
    }
    
    /**
     * 通过字段名从对象或对象的父类中得到字段的值（调用字典的get方法）
     * 
     * @param object 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
     * @throws Exception 异常
     * @Author : ll. create at 2016年4月14日 上午9:18:19
     */
    public static Object getValueOfGet(Object object, String fieldName) throws Exception {
        if (object == null) {
            return null;
        }
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                // 获得get方法
                Method getMethod = pd.getReadMethod();
                // 执行get方法返回一个Object
                return getMethod.invoke(object);
            }
            catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        
        return null;
    }
    
    /**
     * 通过字段名从对象或对象的父类中得到字段的值（调用字典的get方法，可以取出复杂的对象的值）
     * 
     * @param object 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:18:19
     */
    public static Object getValueOfGetIncludeObjectFeild(Object object, String fieldName) throws Exception {
        
        if (object == null) {
            return null;
        }
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                if (fieldName.contains(".")) {
                    // 如：operatorUser.name、operatorUser.org.name，递归调用
                    String[] splitFiledName = fieldName.split("\\.");
                    return getValueOfGetIncludeObjectFeild(getValueOfGetIncludeObjectFeild(object, splitFiledName[0]),
                            splitFiledName[1]);
                }
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                // 获得get方法
                Method getMethod = pd.getReadMethod();
                // 执行get方法返回一个Object
                return getMethod.invoke(object);
            }
            catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        
        return null;
    }
    
}