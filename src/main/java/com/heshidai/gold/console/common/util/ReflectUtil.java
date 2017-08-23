/******************************************************************************
 * Copyright (C) 2014 ShenZhen HeShiDai andy.zl Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.heshidai.gold.console.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 反射调用工具类(带缓存方法的Map,减少反射调用方法时查找方法所用的时间)
 * @version 2017年2月5日下午1:37:24
 * @author dx.love
 */
public abstract class ReflectUtil {
    /**
     * 缓存方法的Map
     */
    private static Map<String, Method> METHOD_CACHE_MAP = new HashMap<String, Method>();
    
    /**
     * 
     * 调用
     * 
     * @author andy.zl
     * @date 2014-11-10下午2:35:22
     * @param obj obj
     * @param methodName 方法表
     * @param params 变量
     * @return 结果
     */
    public static Object invoke(Object obj, String methodName, Object... params) {
        String key = obj.getClass().getName() + "." + methodName;
        Method m = METHOD_CACHE_MAP.get(key);
        if (m == null) {
            try {
                m = obj.getClass().getDeclaredMethod(methodName);
            }
            catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            if (m != null) {
                METHOD_CACHE_MAP.put(key, m);
            }
        }
        if (m != null) {
            try {
                return m.invoke(obj);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 反射调用get方法 
     * 
     * @author andy.zl
     * @date 2014-11-10下午3:56:54
     * @param obj obj
     * @param attributeName 属性名称
     * @param params 变量
     * @return obj
     */
    public static Object invokeGet(Object obj, String attributeName, Object... params) {
        String methodName = "get" + attributeName.substring(0, 1).toUpperCase()
                + attributeName.substring(1, attributeName.length());
        return invoke(obj, methodName, params);
    }
    
    /**
     * 反射调用set方法 描述
     * 
     * @author andy.zl
     * @date 2014-11-10下午3:58:10
     * @param obj obj
     * @param attributeName 属性名称
     * @param params 变量
     */
    public static void invokeSet(Object obj, String attributeName, Object... params) {
        String methodName = "set" + attributeName.substring(0, 1).toUpperCase()
                + attributeName.substring(1, attributeName.length());
        invoke(obj, methodName, params);
    }
}
