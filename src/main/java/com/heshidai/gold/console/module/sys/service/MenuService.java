package com.heshidai.gold.console.module.sys.service;

import java.util.List;

import com.heshidai.gold.console.module.sys.entity.SysMenu;

/**
 * 功能：权限service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
public interface MenuService {
    
    /**
     * 功能：查询用户所有权限
     *
     * @version 2016年12月28日下午9:30:01
     * @author baocheng.ren
     * @param userId 用户id
     * @return List<String>
     */
    public List<String> getPermissionByUserId(String userId);
    
    /**
     * 功能：根据角色id查询权限
     *
     * @version 2016年12月26日下午4:14:22
     * @author baocheng.ren
     * @param roleIds 角色id
     * @return List<SysMenu>
     */
    public List<SysMenu> findPermission(String[] roleIds);
    
    /**
     * 功能：保存修改的权限
     *
     * @version 2016年12月9日下午1:46:41
     * @author baocheng.ren
     * @param permissionIds 权限id
     * @param roleIds 角色id
     * @return
     */
    public void savePermission(String[] permissionIds, String[] roleIds);
    
}
