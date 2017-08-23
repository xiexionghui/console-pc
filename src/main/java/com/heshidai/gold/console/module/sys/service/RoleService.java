package com.heshidai.gold.console.module.sys.service;

import java.util.List;

import com.heshidai.gold.console.module.sys.entity.page.RolePage;

/**
 * 功能：角色service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
public interface RoleService {
    
    /**
     * 功能：查询角色集合
     *
     * @version 2016年12月9日上午9:20:11
     * @author baocheng.ren
     * @param page 分页
     * @return
     */
    public void findList(RolePage page);
    
    /**
     * 功能：保存修改的用户角色
     *
     * @version 2016年12月9日下午1:46:41
     * @author baocheng.ren
     * @param roles 角色id
     * @param userIds 用户id
     * @return
     */
    public void saveUserRole(String[] roles, String[] userIds);
    
    /**
     * 功能：查询角色名称集合
     *
     * @version 2016年12月7日上午9:50:50
     * @author baocheng.ren
     * @param userId 用户id
     * @return List<String>
     */
    public List<String> getRoleNameByUserId(String userId);
    
    /**
     * 功能：删除角色，并删除角色与权限的关系
     *
     * @version 2016年12月13日上午10:44:32
     * @author baocheng.ren
     * @param roleIds 角色id
     */
    public void deleteRole(String[] roleIds);
    
}
