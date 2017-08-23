/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.heshidai.gold.console.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 * 
 * @author calvin
 * @version 2013-01-15
 */
public class Exceptions {
    
    /**
     * 功能：将CheckedException转换为UncheckedException
     *
     * @version 2017年1月4日下午2:25:40
     * @author baocheng.ren
     * @param e e
     * @return RuntimeException
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        else {
            return new RuntimeException(e);
        }
    }
    
    /**
     * 功能：将ErrorStack转化为String
     *
     * @version 2017年1月4日下午2:26:01
     * @author baocheng.ren
     * @param e e
     * @return String
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    /**
     * 功能：判断异常是否由某些底层的异常引起
     *
     * @version 2017年1月4日下午2:26:15
     * @author baocheng.ren
     * @param ex ex
     * @param causeExceptionClasses causeExceptionClasses
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
