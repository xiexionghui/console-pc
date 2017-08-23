package com.heshidai.gold.console.module.sys.service;

import com.heshidai.gold.console.module.sys.entity.ShiroUser;
import com.heshidai.gold.console.module.sys.entity.SysUser;
import com.heshidai.gold.console.module.sys.entity.page.SysUserPage;

/**
 * 功能：用户信息服务类
 *
 * @version 2016年8月5日下午2:47:06
 * @author baocheng.ren
 */
public interface SysUserService {
    /**
     * 功能：根据用户名查询基本信息
     *
     * @version 2016年12月26日下午4:32:53
     * @author baocheng.ren
     * @param username 用户名
     * @return ShiroUser
     */
    public ShiroUser getByLoginName(String username);   
    
    /**
     * 功能：查询用户信息列表
     *
     * @version 2016年8月16日下午4:36:17
     * @author baocheng.ren
     * @param page 分页
     */
    public void findList(SysUserPage page);
    
    /**
     * 功能：添加用户信息
     *
     * @version 2016年12月27日上午10:46:10
     * @author baocheng.ren
     * @param sysUser 对象
     * @return int
     */
    public int addUse(SysUser sysUser);
    
    /**
     * 功能：修改用户信息
     *
     * @version 2016年12月27日上午10:46:10
     * @author baocheng.ren
     * @param sysUser 对象
     * @return int
     */
    public int updateUse(SysUser sysUser);
    
    /**
     * 功能：删除
     *
     * @version 2016年12月28日下午4:21:50
     * @author baocheng.ren
     * @param ids 主键
     */
    public void delete(String [] ids);
    
    /**
     * 功能：重置密码
     *
     * @version 2017年1月16日下午4:54:51
     * @author baocheng.ren
     * @param ids 用户id
     * @param newPassword 新密码
     */
    public void resetPassword(String[] ids, String newPassword);
    
    /**
     * 查询平台账户信息
     * 
     * @version 2017-7-11上午11:25:31
     * @author jian.xiao
     * @param mobilePhone
     * @return
     */
    public String queryUser(String mobilePhone);
    
    /**
     * 平台账号关联
     *
     * @version 2017-7-11上午11:43:01
     * @author jian.xiao
     * @param customerId
     * @param id
     * @return
     */
    public String relation(String id, String customerId);
}
