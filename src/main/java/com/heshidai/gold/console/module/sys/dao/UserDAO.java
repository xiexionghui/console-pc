package com.heshidai.gold.console.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.ShiroUser;
import com.heshidai.gold.console.module.sys.entity.SysUser;
import com.heshidai.gold.console.module.sys.entity.page.SysUserPage;

/**
 * 功能：用户信息类
 *
 * @version 2016年12月26日下午3:01:59
 * @author baocheng.ren
 */
@Repository
public interface UserDAO extends BasicDAO<SysUser> {
    
    /**
     * 功能：根据用户登录名查询用户信息
     *
     * @version 2016年12月26日下午4:02:54
     * @author baocheng.ren
     * @param loginName 登录名
     * @return ShiroUser
     */
    ShiroUser getByLoginName(String loginName);
    
    /**
     * 功能:查询列表数据数量
     *
     * @version 2016年12月26日下午7:16:44
     * @author baocheng.ren
     * @param page 分页类
     * @return int
     */
    int findUserListCount(SysUserPage page);
    
    /**
     * 功能:查询列表数据集合
     *
     * @version 2016年12月26日下午7:16:44
     * @author baocheng.ren
     * @param page 分页类
     * @return List<Object>
     */
    List<Object> findUserList(SysUserPage page);
    
    /**
     * 功能：设置用户状态
     *
     * @version 2016年12月27日下午2:39:59
     * @author baocheng.ren
     * @param status 登录状态
     * @param ids 主键
     * @return int
     */
    int updateStatusById(@Param("status") String status, @Param("ids") String[] ids);
    
    /**
     * 功能：查询登录用户名是否存在
     *
     * @version 2016年12月28日下午4:01:34
     * @author baocheng.ren
     * @param loginName 登录名
     * @param userId 用户id
     * @return SysUser
     */
    SysUser getSysUserByLoginName(@Param("loginName") String loginName, @Param("userId") String userId);
    
    /**
     * 功能：修改密码
     *
     * @version 2016年12月29日上午9:27:42
     * @author baocheng.ren
     * @param id 主键
     * @param password 加密密码
     * @return int
     */
    int updatePassword(@Param("id") String id, @Param("password") String password);
    
    /**
     * 功能：查询原密码是否正确
     *
     * @version 2016年12月29日上午9:47:59
     * @author baocheng.ren
     * @param id 主键
     * @param password 原密码
     * @return String id
     */
    String getUserByPassword(@Param("id") String id, @Param("password") String password);
    
    /**
     * 功能：关联平台账户
     * 
     * @version 2017-7-11上午11:32:20
     * @author jian.xiao
     * @param id
     * @param customerId
     * @return
     */
    int updateRelation(@Param("id") String id, @Param("customerId") String customerId);
}
