package com.heshidai.gold.console.module.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.FollowRecord;

/**
 * 功能：跟进记录dao层
 *
 * @version 2016年8月5日下午2:45:45
 * @author baocheng.ren
 */
@Repository
public interface FollowRecordDAO extends BasicDAO<FollowRecord> {
    
    /**
     * 功能：条件查询跟进记录是否存在
     *
     * @version 2017年2月16日上午10:14:30
     * @author baocheng.ren
     * @param bizId bizId
     * @param type type
     * @return String
     */
    String getBybizIdAndType(@Param("bizId") String bizId, @Param("type") String type);
    
}
