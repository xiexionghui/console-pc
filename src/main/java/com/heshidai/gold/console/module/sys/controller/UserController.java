/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heshidai.gold.console.common.action.BaseController;
import com.heshidai.gold.console.common.util.Exceptions;
import com.heshidai.gold.console.common.util.ResultUtil;
import com.heshidai.gold.console.module.sys.dao.OrganDAO;
import com.heshidai.gold.console.module.sys.dao.UserDAO;
import com.heshidai.gold.console.module.sys.entity.SysUser;
import com.heshidai.gold.console.module.sys.entity.page.SysUserPage;
import com.heshidai.gold.console.module.sys.service.SysUserService;

/**
 * 功能：用户信息相关控制层
 *
 * @version 2016年8月16日下午5:09:47
 * @author baocheng.ren
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * 用户信息service
     */
    @Resource
    private SysUserService sysUserService;
    
    /**
     * 用户信息dao
     */
    @Resource
    private UserDAO sysUserDAO;
    
    /**
     * 组织机构dao
     */
    @Resource
    private OrganDAO organDAO;
    
    
    /**
     * 功能：跳转到用户信息列表界面
     *
     * @version 2016年12月26日下午6:00:16
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("findListUI")
    public String findListTestUI() {
        return "modules/sys/user/user-list";
    }
    
    /**
     * 功能：查询用户信息列表
     *
     * @version 2016年12月26日下午6:00:34
     * @author baocheng.ren
     * @param model model
     * @param page page
     * @return String
     */
    @RequestMapping("findList")
    @ResponseBody
    public String findListTest(Model model, SysUserPage page) {
        this.sysUserService.findList(page);
        return ResultUtil.getJsonString(page.getDatas(), page.getTotalElements());
    }
    
    /**
     * 功能：删除用户信息
     *
     * @version 2016年12月27日下午7:01:49
     * @author baocheng.ren
     * @param ids id
     * @return String
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String[] ids) {
        try {
            this.sysUserService.delete(ids);
        }
        catch (Exception e) {
            log.error(Exceptions.getStackTraceAsString(e));
            return "false";
        }
        return "true";
    }
    
    /**
     * 功能：跳转到添加用户界面
     *
     * @version 2016年12月27日下午7:02:47
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("addUserUI")
    public String addUserUI() {
        return "modules/sys/user/user-add";
    }
    
    /**
     * 功能：添加用户信息
     *
     * @version 2016年12月27日下午7:03:00
     * @author baocheng.ren
     * @param sysUser 对象
     * @return String
     */
    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(SysUser sysUser) {
        log.debug("添加用户:{}", sysUser);
        SysUser sysUserByLoginName = this.sysUserDAO.getSysUserByLoginName(sysUser.getLoginName(), null);
        if (sysUserByLoginName != null) {
            return "loginNameExists";
        }
        int count = sysUserService.addUse(sysUser);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：跳转到修改界面
     *
     * @version 2016年12月27日上午11:07:47
     * @author baocheng.ren
     * @param model model
     * @param id 主键
     * @return ids
     */
    @RequestMapping("editUserUI")
    public String editUserUI(Model model, String id) {
        SysUser sysUser = sysUserDAO.findById(id);
        model.addAttribute("sysUser", sysUser);
        return "modules/sys/user/user-edit";
    }
    
    /**
     * 功能：保存修改的用户信息
     *
     * @version 2016年12月27日上午11:08:04
     * @author baocheng.ren
     * @param sysUser 对象
     * @return String
     */
    @RequestMapping("editUse")
    @ResponseBody
    public String editUse(SysUser sysUser) {
        log.debug("修改用户:{}", sysUser);
        SysUser sysUserByLoginName = this.sysUserDAO.getSysUserByLoginName(sysUser.getLoginName(), sysUser.getId());
        if (sysUserByLoginName != null) {
            return "loginNameExists";
        }
        int count = sysUserService.updateUse(sysUser);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：限制登录功能
     *
     * @version 2016年12月27日下午7:04:32
     * @author baocheng.ren
     * @param ids id
     * @return String
     */
    @RequestMapping("astrictLogin")
    @ResponseBody
    public String astrictLogin(String[] ids) {
        int count = sysUserDAO.updateStatusById("1", ids);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：解除限制用户登录功能
     *
     * @version 2016年12月27日下午7:04:32
     * @author baocheng.ren
     * @param ids id
     * @return String
     */
    @RequestMapping("relieveAstrict")
    @ResponseBody
    public String relieveAstrict(String[] ids) {
        int count = sysUserDAO.updateStatusById("0", ids);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：跳转到修改密码界面
     *
     * @version 2016年12月29日上午9:50:53
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("updatePasswordUI")
    public String updatePasswordUI() {
        return "modules/sys/user/user-password";
    }
    
    /**
     * 功能：修改密码
     *
     * @version 2016年12月29日上午9:50:53
     * @author baocheng.ren
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return String
     */
/*    @RequestMapping("updatePassword")
    @ResponseBody
    public String updatePassword(String oldPassword, String newPassword) {
        String userId = this.sysUserDAO.getUserByPassword(UserUtil.getUserId(), MD5.toDigest(oldPassword));
        if (userId == null) {
            return "oldPasswordError";
        }
        int count = this.sysUserDAO.updatePassword(UserUtil.getUserId(), MD5.toDigest(newPassword));
        return count > 0 ? "true" : "false";
    }*/
    
    /**
     * 功能：跳转到重置密码界面
     *
     * @version 2017年1月16日下午4:58:45
     * @author baocheng.ren
     * @param model model
     * @param ids ids
     * @return String
     */
    @RequestMapping("resetPasswordUI")
    public String resetPasswordUI(Model model, String ids) {
        model.addAttribute("ids", ids);
        return "modules/sys/user/user-reset-password";
    }
    
    /**
     * 功能：重置密码
     *
     * @version 2016年12月29日上午9:50:53
     * @author baocheng.ren
     * @param ids 用户id
     * @param newPassword 新密码
     * @return String
     */
    @RequestMapping("resetPassword")
    @ResponseBody
    public String resetPassword(String[] ids, String newPassword) {
        try {
            this.sysUserService.resetPassword(ids, newPassword);
            return "true";
        }
        catch (Exception e) {
            log.error("resetPassword:{},{}", ids, e);
        }
        return "false";
    }
    
    /**
     * 功能：平台账户关联
     * 
     * @version 2017-7-11上午10:33:24
     * @author jian.xiao
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("relationUI")
    public String relationUI(Model model,String ids){
        model.addAttribute("id", ids);
        return "modules/sys/user/user-relation";
    }
    
    /**
     * 功能：查询用户账户信息
     * 
     * @version 2017-7-11上午11:14:28
     * @author jian.xiao
     * @param mobilePhone
     * @return
     */
    @RequestMapping("queryUser")
    @ResponseBody
    public String queryUser(String mobilePhone){
        if (!StringUtils.isEmpty(mobilePhone)) {
            return sysUserService.queryUser(mobilePhone);
        }
        return null;
    }
    
    /**
     * 功能：关联平台账户
     * 
     * @version 2017-7-11上午11:15:00
     * @author jian.xiao
     * @param id
     * @param mobilePhone
     * @return
     */
    @RequestMapping("relation")
    @ResponseBody
    public String relation(String id, String customerId){
        if (!StringUtils.isEmpty(customerId)) {
            return sysUserService.relation(id, customerId);
        }
        return null;
    }
}
