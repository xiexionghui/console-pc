/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.heshidai.gold.console.common.action.BaseController;
import com.heshidai.gold.console.common.util.Exceptions;
import com.heshidai.gold.console.common.util.ResultUtil;
import com.heshidai.gold.console.common.util.StringUtils;
import com.heshidai.gold.console.module.sys.dao.RoleDAO;
import com.heshidai.gold.console.module.sys.entity.SysRole;
import com.heshidai.gold.console.module.sys.entity.page.RolePage;
import com.heshidai.gold.console.module.sys.service.RoleService;

/**
 * 功能：角色
 *
 * @version 2016年12月8日上午9:20:28
 * @author baocheng.ren
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * 角色service
     */
    @Resource
    private RoleService roleService;
    
    /**
     * 角色dao
     */
    @Resource
    private RoleDAO roleDAO;
    
    /**
     * 功能：跳转到用户信息列表界面
     *
     * @version 2016年12月26日下午6:00:16
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("findListUI")
    public String findListTestUI() {
        return "modules/sys/role/role-list";
    }
    
    /**
     * 功能：查询角色信息列表
     *
     * @version 2016年12月27日下午7:10:07
     * @author baocheng.ren
     * @param model 模型
     * @param page 分页
     * @return String
     */
    @RequestMapping("findList")
    @ResponseBody
    public String findListTest(Model model, RolePage page) {
        this.roleService.findList(page);
        return ResultUtil.getJsonString(page.getDatas(), page.getTotalElements());
    }
    
    /**
     * 功能：跳转到用户角色授权界面
     *
     * @version 2016年12月27日下午3:26:36
     * @author baocheng.ren
     * @param model 模型
     * @param userIds id
     * @return String
     */
    @RequestMapping("roleUserAccreditUI")
    public String roleUserAccreditUI(Model model, String[] userIds) {
        model.addAttribute("userIds", StringUtils.join(userIds, ","));
        return "modules/sys/user/user-accredit";
    }
    
    /**
     * 功能：用户角色信息授权界面信息列表
     *
     * @version 2016年12月27日下午7:10:27
     * @author baocheng.ren
     * @param model 模型
     * @param userIds 用户id
     * @return String
     */
    @RequestMapping("roleUserAccredit")
    @ResponseBody
    public String roleUserAccredit(Model model, String[] userIds) {
        String userId;
        if (userIds.length > 1) {
            userId = "0";
        }
        else {
            userId = userIds[0];
        }
        List<SysRole> sysRoles = this.roleDAO.findAllRoleByUserId(userId);
        return JSON.toJSONString(sysRoles);
    }
    
    /**
     * 功能：保存用户角色关系
     *
     * @version 2016年12月27日下午5:42:03
     * @author baocheng.ren
     * @param userIds id
     * @param roleIds id
     * @return String
     */
    @RequestMapping("saveUserRoleAccredit")
    @ResponseBody
    public String saveUserRoleAccredit(String[] roleIds, String[] userIds) {
        try {
            this.roleService.saveUserRole(roleIds, userIds);
        }
        catch (Exception e) {
            log.error(Exceptions.getStackTraceAsString(e));
            return "false";
        }
        return "true";
    }
    
    /**
     * 功能：跳转到添加角色界面
     *
     * @version 2016年12月27日下午8:27:03
     * @author baocheng.ren
     * @param model 模型
     * @return String
     */
    @RequestMapping("roleAddUI")
    public String roleAddUI(Model model) {
        return "modules/sys/role/role-add";
    }
    
    /**
     * 功能：保存创建的角色
     *
     * @version 2016年12月13日上午10:06:27
     * @author baocheng.ren
     * @param model 模型
     * @param sysRole 对象
     * @return String
     */
    @RequestMapping("roleAdd")
    @ResponseBody
    public String roleAdd(Model model, SysRole sysRole) {
        SysRole byRoleName = this.roleDAO.getByRoleName(sysRole.getName(), null);
        if (byRoleName != null) {
            return "nameAlreadyExist";
        }
        
        int count = this.roleDAO.insert(sysRole);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：跳转到修改角色界面
     *
     * @version 2016年12月27日下午8:28:38
     * @author baocheng.ren
     * @param model 模型
     * @param roleId 角色id
     * @return sysRole
     */
    @RequestMapping("roleEditUI")
    public String roleEditUI(Model model, String roleId) {
        SysRole sysRole = this.roleDAO.findById(roleId);
        model.addAttribute("sysRole", sysRole);
        return "modules/sys/role/role-edit";
    }
    
    /**
     * 功能：保存修改的角色信息
     *
     * @version 2016年12月27日下午8:29:53
     * @author baocheng.ren
     * @param model 模型
     * @param sysRole 对象
     * @return sysRole
     */
    @RequestMapping("roleUpdate")
    @ResponseBody
    public String roleUpdate(Model model, SysRole sysRole) {
        SysRole byRoleName = this.roleDAO.getByRoleName(sysRole.getName(), sysRole.getId());
        if (byRoleName != null) {
            return "nameAlreadyExist";
        }
        int count = this.roleDAO.update(sysRole);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：删除角色
     *
     * @version 2016年12月27日下午8:30:43
     * @author baocheng.ren
     * @param roleIds id
     * @return String
     */
    @RequestMapping("roleDelete")
    @ResponseBody
    public String deleteRole(String[] roleIds) {
        try {
            this.roleService.deleteRole(roleIds);
        }
        catch (Exception e) {
            log.error(Exceptions.getStackTraceAsString(e));
            return "false";
        }
        return "true";
    }
    
}
