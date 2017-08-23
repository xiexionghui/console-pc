package com.heshidai.gold.console.module.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heshidai.gold.console.common.util.MD5;
import com.heshidai.gold.console.common.util.ResultUtil;
import com.heshidai.gold.console.common.util.StringUtils;
import com.heshidai.gold.console.module.sys.dao.UserDAO;
import com.heshidai.gold.console.module.sys.entity.ShiroUser;
import com.heshidai.gold.console.module.sys.entity.SysUser;
import com.heshidai.gold.console.module.sys.entity.page.SysUserPage;
import com.heshidai.gold.console.module.sys.service.SysUserService;

/**
 * 功能：用户信息服务类
 *
 * @version 2016年8月5日下午2:47:06
 * @author baocheng.ren
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    
    /**
     * 查询用户信息dao
     */
    @Autowired
    private UserDAO userDAO;
    
    /**
     * 功能：根据用户名查询基本信息
     *
     * @version 2016年12月26日下午4:32:53
     * @author baocheng.ren
     * @param username 用户名
     * @return ShiroUser
     */
    @Override
    public ShiroUser getByLoginName(String username) {
        ShiroUser shiroUser = userDAO.getByLoginName(username);
        return shiroUser;
    }
    
    /**
     * 功能：查询用户信息列表
     *
     * @version 2016年8月16日下午4:36:17
     * @author baocheng.ren
     * @param page 分页
     */
    @Override
    public void findList(SysUserPage page) {
        int count = this.userDAO.findUserListCount(page);
        if (count > 0) {
            List<Object> findList = this.userDAO.findUserList(page);
            page.setDatas(findList);
            page.setTotalElements(count);
        }
    }
    
    /**
     * 功能：添加用户信息
     *
     * @version 2016年12月27日上午10:46:10
     * @author baocheng.ren
     * @param sysUser 对象
     * @return int
     */
    @Override
    public int addUse(SysUser sysUser) {
        sysUser.setPassword(MD5.toDigest(sysUser.getPassword()));
        int count = this.userDAO.insert(sysUser);
        return count;
    }
    
    /**
     * 功能：修改用户信息
     *
     * @version 2016年12月27日上午10:46:10
     * @author baocheng.ren
     * @param sysUser 对象
     * @return int
     */
    @Override
    public int updateUse(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getDimissionTime())) {
            sysUser.setDimissionTime(null);
        }
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(MD5.toDigest(sysUser.getPassword()));
        }
        int count = this.userDAO.update(sysUser);
        return count;
    }
    
    /**
     * 功能：删除
     *
     * @version 2016年12月28日下午4:21:50
     * @author baocheng.ren
     * @param ids 主键
     */
    @Override
    @Transactional
    public void delete(String[] ids) {
        for (String id : ids) {
            int count = this.userDAO.deleteById(id);
            if (count <= 0) {
                throw new RuntimeException("删除失败");
            }
        }
    }
    
    /**
     * 功能：重置密码
     *
     * @version 2017年1月16日下午4:54:51
     * @author baocheng.ren
     * @param ids 用户id
     * @param newPassword 新密码
     */
    @Override
    @Transactional
    public void resetPassword(String[] ids, String newPassword) {
        for (String id : ids) {
            int count = this.userDAO.updatePassword(id, MD5.toDigest(newPassword));
            if (count <= 0) {
                throw new RuntimeException("重置密码失败");
            }
        }
    }


    @Override
    public String relation(String id, String customerId) {
        int updated = this.userDAO.updateRelation(id, customerId);
        if (updated == 1) {
            return ResultUtil.successJson();
        }
        else {
            return ResultUtil.failJson("关联平台账户失败！");
        }
    }

    @Override
    public String queryUser(String mobilePhone) {
        // TODO Auto-generated method stub
        return null;
    }
}
