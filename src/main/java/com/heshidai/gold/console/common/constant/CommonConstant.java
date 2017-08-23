/******************************************************************************
 * Copyright (C) 2014 ShenZhen HeShiDai andy.zl Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.heshidai.gold.console.common.constant;

/**
 * 功能：用到的常量
 *
 * @version 2016年9月23日下午3:03:10
 * @author baocheng.ren
 */
public abstract class CommonConstant {
    /**
     * 用户名键值
     */
    public static final String USER_NAME = "username";
    
    /**
     * 验证码保存在session中的键值
     */
    public static final String VALIDATE_CODE_SESSION_KEY = "validateCode";
    
    /**
     * 登录界面传递的获取验证码参数值
     */
    public static final String VALIDATE_CODE_PARAMETER = "validateCode";
    
    /**
     * 验证码输入错误时，设置验证码错误时的名称
     */
    public static final String VALIDATE_CODE_ERROR_VALUE = "randomCodeError";
    
    /**
     * 公章路径
     */
    public static final String HSDGOLD_SEAL_PATH = "/static/images/hsdgold_seal.png";
    
    /**
     * 电销人员查询标记
     */
    public static final String TELEMARKETER_QUERY_FLAG ="telemarketerQuery";
    
}
