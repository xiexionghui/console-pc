/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.module.sys.controller;

import java.util.ArrayList;
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
import com.heshidai.gold.console.module.sys.dao.OrganDAO;
import com.heshidai.gold.console.module.sys.entity.SysOrgan;
import com.heshidai.gold.console.module.sys.service.OrganService;

/**
 * 功能：组织机构control
 *
 * @version 2016年12月8日上午9:20:28
 * @author baocheng.ren
 */
@Controller
@RequestMapping("organ")
public class OrganController extends BaseController {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * 组织机构service
     */
    @Resource
    private OrganService organService;
    
    /**
     * 组织机构dao
     */
    @Resource
    private OrganDAO organDAO;
    
    /**
     * 功能：跳转到列表界面，供用户添加、修改选择部门
     *
     * @version 2016年12月26日下午6:00:16
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("organUserAddUI")
    public String organUserAddUI() {
        return "modules/sys/organ/organ-user-add";
    }
    
    /**
     * 功能：查询组织机构树形列表，供用户添加、修改选择部门
     *
     * @version 2016年12月26日下午4:41:36
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("organUserAdd")
    @ResponseBody
    public String organUserAdd() {
        List<SysOrgan> sysOrgans = this.organService.findListShu(null);
        return JSON.toJSONString(sysOrgans);
    }
    
    /**
     * 功能：跳转到列表界面，供部门修改使用，选择部门
     *
     * @version 2016年12月26日下午6:00:16
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("organEditShuUI")
    public String organEditShuUI() {
        return "modules/sys/organ/organ-edit-shu";
    }
    
    /**
     * 功能：查询组织机构树形列表，供部门修改使用，选择部门
     *
     * @version 2016年12月26日下午4:41:36
     * @author baocheng.ren
     * @param organId organId
     * @return String
     */
    @RequestMapping("organEditShu")
    @ResponseBody
    public String organEditShu(String organId) {
        List<SysOrgan> sysOrgans = new ArrayList<SysOrgan>();
        SysOrgan sysOrgan = new SysOrgan();
        sysOrgan.setId("0");
        sysOrgan.setName("一级部门");
        sysOrgans.add(sysOrgan);
        sysOrgans.addAll(this.organService.findListShu(organId));
        return JSON.toJSONString(sysOrgans);
    }
    
    /**
     * 功能：跳转树列表界面
     *
     * @version 2016年12月26日下午6:00:16
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("findListShuUI")
    public String findListShuUI() {
        return "modules/sys/organ/organ-list-shu";
    }
    
    /**
     * 功能：查询组织机构树形列表
     *
     * @version 2016年12月26日下午4:41:36
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("findListShu")
    @ResponseBody
    public String findListShu() {
        List<SysOrgan> sysOrgans = this.organService.findListShu(null);
        return ResultUtil.getJsonString(sysOrgans, Long.valueOf(sysOrgans.size()));
    }
    
    /**
     * 功能：跳转到添加界面
     *
     * @version 2016年12月28日下午9:12:27
     * @author baocheng.ren
     * @return String
     */
    @RequestMapping("addUI")
    public String addUI() {
        return "modules/sys/organ/organ-add";
    }
    
    /**
     * 功能：保存添加
     *
     * @version 2016年12月13日上午10:06:27
     * @author baocheng.ren
     * @param model 模型
     * @param sysOrgan 类
     * @return String
     */
    @RequestMapping("add")
    @ResponseBody
    public String add(Model model, SysOrgan sysOrgan) {
        SysOrgan byNameAndParentId = this.organDAO.getByNameAndParentId(sysOrgan.getName(), sysOrgan.getParentId(),
                null);
        if (byNameAndParentId != null) {
            return "nameAlreadyExist";
        }
        int count = this.organService.save(sysOrgan);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：跳转到修改界面
     *
     * @version 2016年12月27日下午8:28:38
     * @author baocheng.ren
     * @param model 模型
     * @param id id
     * @return String
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String id) {
        SysOrgan sysOrgan = this.organDAO.findById(id);
        model.addAttribute("sysOrgan", sysOrgan);
        return "modules/sys/organ/organ-edit";
    }
    
    /**
     * 功能：保存修改
     *
     * @version 2016年12月27日下午8:29:53
     * @author baocheng.ren
     * @param model 模型
     * @param sysOrgan 类
     * @return String
     */
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Model model, SysOrgan sysOrgan) {
        SysOrgan byNameAndParentId = this.organDAO.getByNameAndParentId(sysOrgan.getName(), sysOrgan.getParentId(),
                sysOrgan.getId());
        if (byNameAndParentId != null) {
            return "nameAlreadyExist";
        }
        int count = this.organDAO.update(sysOrgan);
        return count > 0 ? "true" : "false";
    }
    
    /**
     * 功能：删除
     *
     * @version 2016年12月27日下午8:30:43
     * @author baocheng.ren
     * @param id 主键
     * @return String
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id) {
        try {
            this.organService.delete(id);
        }
        catch (Exception e) {
            log.error(Exceptions.getStackTraceAsString(e));
            return "false";
        }
        return "true";
    }
}
