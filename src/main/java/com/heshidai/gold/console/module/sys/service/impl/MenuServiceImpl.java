package com.heshidai.gold.console.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heshidai.gold.console.common.util.StringUtils;
import com.heshidai.gold.console.module.sys.dao.MenuDAO;
import com.heshidai.gold.console.module.sys.entity.SysMenu;
import com.heshidai.gold.console.module.sys.service.MenuService;

/**
 * 功能：权限service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    /**
     * 顶目录父id是0
     */
    private static final String PARENT_ID = "0";
    
    @Resource
    private MenuDAO menuDAO;
    
    /**
     * 根据用户id查询权限集合
     */
    @Override
    public List<String> getPermissionByUserId(String userId) {
        return menuDAO.findByUserId(userId);
    }
    
    /**
     * 功能：查询所有权限，根据角色id查询已授予的权限
     *
     * @version 2016年12月26日下午4:14:22
     * @author baocheng.ren
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> findPermission(String[] roleIds) {
        String roleId;
        if (roleIds.length > 1) {
            roleId = "0";
        }
        else {
            roleId = roleIds[0];
        }
        
        List<SysMenu> sysMenus = new ArrayList<SysMenu>();
        List<SysMenu> findSysMenu = menuDAO.findPermission(roleId, PARENT_ID);
        for (SysMenu sysMenu : findSysMenu) {
            sysMenus.add(sysMenu);
            this.getPermission(sysMenu, sysMenus);
        }
        return sysMenus;
    }
    
    /**
     * 功能：保存修改的权限
     *
     * @version 2016年12月9日下午1:46:41
     * @author baocheng.ren
     * @param permissionId
     * @param roleId
     * @param itemId
     * @return
     */
    @Override
    @Transactional
    public void savePermission(String[] permissionIds, String[] roleIds) {
        for (String roleId : roleIds) {
            this.menuDAO.deleteByRoleId(roleId);
            if (permissionIds == null || permissionIds.length <= 0) {
                continue;
            }
            this.menuDAO.savePermissions(permissionIds, roleId);
        }
    }
    
    /**
     * 功能：查询所有的目录
     *
     * @version 2016年12月28日下午9:31:09
     * @author baocheng.ren
     * @param into 输入类
     * @param out 结果类
     */
    private void getPermission(SysMenu into, List<SysMenu> out) {
        if (into == null || StringUtils.isBlank(into.getParentId()) || StringUtils.isBlank(into.getId())) {
            return;
        }
        List<SysMenu> sysMenus = menuDAO.findPermission(into.getRoleId(), into.getId());
        for (SysMenu sysMenu : sysMenus) {
            out.add(sysMenu);
            this.getPermission(sysMenu, out);
        }
    }
}
