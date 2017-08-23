package com.heshidai.gold.console.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.SysRole;

/**
 * 功能：角色dao层
 *
 * @version 2016年8月5日下午2:45:25
 * @author baocheng.ren
 */
@Repository
public interface RoleDAO extends BasicDAO<SysRole> {
    /**
     * 功能：根据用户id查询用户对应角色
     *
     * @version 2016年12月13日下午4:06:24
     * @author baocheng.ren
     * @param userId 用户id
     * @return List<SysRole>
     */
    public List<SysRole> findByUserId(String userId);
    
    /**
     * 功能：根据userId删除用户角色
     *
     * @version 2016年12月9日下午6:19:06
     * @author baocheng.ren
     * @param userId 用户id
     * @return int
     */
    public int deleteByUserId(String userId);
    
    /**
     * 功能：根据角色id删除
     *
     * @version 2016年12月9日下午6:19:06
     * @author baocheng.ren
     * @param roleId 角色id
     * @return int
     */
    public int deleteByRoleId(String roleId);
    
    /**
     * 功能：功能：增加用户角色关系
     *
     * @version 2016年12月26日下午3:39:15
     * @author baocheng.ren
     * @param roleIds 角色id
     * @param userId 用户id
     * @return int
     */
    public int insertUserRole(@Param("roleIds") String[] roleIds, @Param("userId") String userId);
    
    /**
     * 功能：查询全部角色，并查询当前用户具备的角色
     *
     * @version 2016年12月27日下午3:22:06
     * @author baocheng.ren
     * @param userId 用户id
     * @return List<SysRole>
     */ 
    public List<SysRole> findAllRoleByUserId(@Param("userId") String userId);
    
    /**
     * 功能：查询角色名称是否存在
     *
     * @version 2016年12月27日下午8:44:34
     * @author baocheng.ren
     * @param name 角色名称
     * @param roleId 角色id
     * @return SysRole
     */
    public SysRole getByRoleName(@Param("name") String name, @Param("roleId") String roleId);
}
