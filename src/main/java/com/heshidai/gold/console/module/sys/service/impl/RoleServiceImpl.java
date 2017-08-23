package com.heshidai.gold.console.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heshidai.gold.console.module.sys.dao.MenuDAO;
import com.heshidai.gold.console.module.sys.dao.RoleDAO;
import com.heshidai.gold.console.module.sys.entity.SysRole;
import com.heshidai.gold.console.module.sys.entity.page.RolePage;
import com.heshidai.gold.console.module.sys.service.RoleService;

/**
 * 功能：角色service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Resource
    private RoleDAO roleDAO;
    
    @Resource
    private MenuDAO menuDAO;
    
    /**
     * 功能：查询角色集合
     *
     * @version 2016年12月9日上午9:20:11
     * @author baocheng.ren
     * @param page
     * @return
     */
    @Override
    public void findList(RolePage page) {
        int count = this.roleDAO.findCount(page);
        if (count > 0) {
            List<Object> findList = this.roleDAO.findList(page);
            page.setTotalElements(count);
            page.setDatas(findList);
        }
    }
    
    /**
     * 功能：保存修改的用户角色
     *
     * @version 2016年12月9日下午1:46:41
     * @author baocheng.ren
     * @param roles
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public void saveUserRole(String[] roleIds, String[] userIds) {
        for (String userId : userIds) {
            this.roleDAO.deleteByUserId(userId);
            if (roleIds == null || roleIds.length <= 0) {
                continue;
            }
            this.roleDAO.insertUserRole(roleIds, userId);
        }
    }
    
    /**
     * 功能：查询角色名称集合
     *
     * @version 2016年12月7日上午9:50:50
     * @author baocheng.ren
     * @param userId
     * @return
     */
    @Override
    public List<String> getRoleNameByUserId(String userId) {
        List<String> roles = new ArrayList<String>();
        List<SysRole> sysRoles = roleDAO.findByUserId(userId);
        for (SysRole sysRole : sysRoles) {
            roles.add(sysRole.getName());
        }
        return roles;
    }
    
    /**
     * 功能：删除角色，并删除角色与权限的关系
     *
     * @version 2016年12月13日上午10:44:32
     * @author baocheng.ren
     * @param roleId
     * @param itemId
     */
    @Override
    @Transactional
    public void deleteRole(String[] roleIds) {
        for (String roleId : roleIds) {
            this.roleDAO.deleteByRoleId(roleId);
            this.menuDAO.deleteByRoleId(roleId);
        }
    }
}
