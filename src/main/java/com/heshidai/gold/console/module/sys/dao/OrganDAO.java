package com.heshidai.gold.console.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.SysOrgan;

/**
 * 功能:组织机构dao层
 *
 * @version 2016年8月5日下午2:45:45
 * @author baocheng.ren
 */
@Repository
public interface OrganDAO extends BasicDAO<SysOrgan> {
    
    /**
     * 功能：根据父id查询组织机构
     *
     * @version 2016年12月28日下午6:08:44
     * @author baocheng.ren
     * @param parentId 父id
     * @return List<SysOrgan>
     */
    public List<SysOrgan> findByParentId(String parentId);
    
    /**
     * 功能：查询部门名称是否已存在
     *
     * @version 2016年12月28日下午8:35:59
     * @author baocheng.ren
     * @param name 部门名称
     * @param parentId 父id
     * @param organId 主键
     * @return SysOrgan
     */
    public SysOrgan getByNameAndParentId(@Param("name") String name, @Param("parentId") String parentId,
            @Param("organId") String organId);
    
}
