package com.heshidai.gold.console.common.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.heshidai.gold.console.common.util.Exceptions;
import com.heshidai.gold.console.common.util.StringUtils;

/**
 * 
 * 通用异常处理 记录日志，并跳转错误页面
 * 
 * @version 2016年7月21日上午10:09:07
 * @author du.dxlove
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {
    
    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    
    private static final int NUMBER_100 = 100;
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) {
        
        StringBuilder params = new StringBuilder();
        int index = 0;
        for (Object param : request.getParameterMap().keySet()) {
            params.append((index++ == 0 ? "" : "&") + param + "=");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((String) param, "password") 
                    ? "" : request.getParameter((String) param), NUMBER_100));
        }
        
        logger.error("Class" + handler + "  params:" + params.toString() + " Exception:"
                + Exceptions.getStackTraceAsString(exception));
        
        if (exception instanceof IOException) {
            return new ModelAndView("error/500");
        }
        else if (exception instanceof SQLException) {
            return new ModelAndView("error/500");
        }
        
        return new ModelAndView("error/500");
    }
    
}
