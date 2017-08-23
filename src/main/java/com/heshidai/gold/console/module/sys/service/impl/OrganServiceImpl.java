package com.heshidai.gold.console.module.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heshidai.gold.console.common.util.StringUtils;
import com.heshidai.gold.console.module.sys.dao.OrganDAO;
import com.heshidai.gold.console.module.sys.entity.SysOrgan;
import com.heshidai.gold.console.module.sys.service.OrganService;

/**
 * 功能：组织机构service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
@Service
public class OrganServiceImpl implements OrganService {
    
    /**
     * 顶目录父id是0
     */
    private static final String PARENT_ID = "0";
    
    @Resource
    private OrganDAO organDAO;
    
    /**
     * 功能：查询组织机构树形列表
     *
     * @version 2016年12月28日下午6:11:32
     * @author baocheng.ren
     * @param organId organId
     * @return
     */
    @Override
    public List<SysOrgan> findListShu(String organId) {
        List<SysOrgan> sysOrgans = this.organDAO.findByParentId(PARENT_ID);
        for (SysOrgan sysOrgan : sysOrgans) {
            if (!sysOrgan.getId().equals(organId)) {
                this.setSysOrganChildren(sysOrgan, organId);
            }
        }
        return sysOrgans;
    }
    
    /**
     * 功能：循环设置组织结构
     *
     * @version 2016年12月28日下午9:29:21
     * @author baocheng.ren
     * @param organId organId
     * @param info 输入列
     */
    private void setSysOrganChildren(SysOrgan info, String organId) {
        if (info == null || StringUtils.isBlank(info.getId())) {
            return;
        }
        List<SysOrgan> children = this.organDAO.findByParentId(info.getId());
        info.setChildren(children);
        for (SysOrgan sysOrgan : children) {
            if (!sysOrgan.getId().equals(organId)) {
                this.setSysOrganChildren(sysOrgan, organId);
            }
        }
    }
    
    /**
     * 功能：保存
     *
     * @version 2016年12月28日上午9:41:08
     * @author baocheng.ren
     * @param sysOrgan
     */
    @Override
    public int save(SysOrgan sysOrgan) {
        if (StringUtils.isBlank(sysOrgan.getParentId())) {
            sysOrgan.setParentId("0");
        }
        if (StringUtils.isBlank(sysOrgan.getSort())) {
            sysOrgan.setSort("0");
        }
        return this.organDAO.insert(sysOrgan);
    }
    
    /**
     * 功能：删除
     *
     * @version 2016年12月29日上午10:52:03
     * @author baocheng.ren
     * @param id 部门id
     */
    @Override
    @Transactional
    public void delete(String id) {
        this.organDAO.deleteById(id);
        List<SysOrgan> sysOrgans = this.organDAO.findByParentId(id);
        for (SysOrgan sysOrgan : sysOrgans) {
            this.deleteSysOrganChildren(sysOrgan);
        }
    }
    
    /**
     * 功能：循环删除
     *
     * @version 2016年12月29日上午11:00:46
     * @author baocheng.ren
     * @param info 删除对象
     */
    private void deleteSysOrganChildren(SysOrgan info) {
        if (info == null || StringUtils.isBlank(info.getId())) {
            return;
        }
        this.organDAO.deleteById(info.getId());
        List<SysOrgan> children = this.organDAO.findByParentId(info.getId());
        for (SysOrgan sysOrgan : children) {
            this.deleteSysOrganChildren(sysOrgan);
        }
    }
    
}
