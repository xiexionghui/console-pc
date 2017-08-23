/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.heshidai.gold.console.common.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 功能：以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext
 *
 * @version 2017年1月4日下午2:33:47
 * @author baocheng.ren
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    
    private static ApplicationContext APPLICATION_CONTEXT = null;
    
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    
    /**
     * 功能：取得存储在静态变量中的ApplicationContext
     *
     * @version 2017年1月4日下午2:34:57
     * @author baocheng.ren
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return APPLICATION_CONTEXT;
    }
    
    /**
     * 功能：从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     *
     * @version 2017年1月4日下午2:35:17
     * @author baocheng.ren
     * @param name 名称
     * @param <T> object
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) APPLICATION_CONTEXT.getBean(name);
    }
    
    /**
     * 功能：从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     *
     * @version 2017年1月4日下午2:35:36
     * @author baocheng.ren
     * @param requiredType 类型
     * @param <T> object
     * @return <T>
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return APPLICATION_CONTEXT.getBean(requiredType);
    }
    
    /**
     * 功能：清除SpringContextHolder中的ApplicationContext为Null
     *
     * @version 2017年1月4日下午2:35:55
     * @author baocheng.ren
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + APPLICATION_CONTEXT);
        }
        APPLICATION_CONTEXT = null;
    }
    
    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.APPLICATION_CONTEXT = applicationContext;
    }
    
    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }
    
    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(APPLICATION_CONTEXT != null,
                "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}