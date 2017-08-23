package com.heshidai.gold.console.module.sys.service;

import java.util.List;

import com.heshidai.gold.console.module.sys.entity.SysOrgan;

/**
 * 功能：组织机构service层
 *
 * @version 2016年8月5日下午2:46:10
 * @author baocheng.ren
 */
public interface OrganService {
    
    /**
     * 功能：查询组织机构树形列表
     *
     * @version 2016年12月28日下午6:11:32
     * @author baocheng.ren
     * @param organId organId
     * @return Map<String, Object>
     */
    public List<SysOrgan> findListShu(String organId);
    
    /**
     * 功能：保存
     *
     * @version 2016年12月28日下午9:28:40
     * @author baocheng.ren
     * @param sysOrgan 对象
     * @return int
     */
    public int save(SysOrgan sysOrgan);
    
    /**
     * 功能：删除
     *
     * @version 2016年12月29日上午10:52:03
     * @author baocheng.ren
     * @param id 部门id
     */
    public void delete(String id);

}
