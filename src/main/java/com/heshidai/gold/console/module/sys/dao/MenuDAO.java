package com.heshidai.gold.console.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.SysMenu;

/**
 * 功能：权限dao层
 *
 * @version 2016年8月5日下午2:45:45
 * @author baocheng.ren
 */
@Repository
public interface MenuDAO extends BasicDAO<SysMenu> {
    /**
     * 功能：根据用户id查询权限集合
     *
     * @version 2016年12月26日下午2:59:30
     * @author baocheng.ren
     * @param userId id
     * @return List<String>
     */
    List<String> findByUserId(String userId);
    
    /**
     * 功能：根据角色id、父目录id查询权限
     *
     * @version 2016年12月8日上午9:55:44
     * @author baocheng.ren
     * @param roleId 角色id
     * @param parentId 父目录
     * @return List<SysMenu>
     */
    List<SysMenu> findPermission(@Param("roleId") String roleId, @Param("parentId") String parentId);
    
    /**
     * 功能：根据项目id和角色id删除项目的角色权限
     *
     * @version 2016年12月9日下午1:51:47
     * @author baocheng.ren
     * @param roleId id
     * @return  int
     */
    int deleteByRoleId(@Param("roleId") String roleId);
    
    /**
     * 功能：保存角色权限关系
     *
     * @version 2016年12月26日下午3:14:24
     * @author baocheng.ren
     * @param menus menu
     * @param roleId id
     * @return int
     */
    int savePermissions(@Param("menus") String[] menus, @Param("roleId") String roleId);
    
}
