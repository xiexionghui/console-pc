package com.heshidai.gold.console.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.heshidai.gold.console.common.dao.BasicDAO;
import com.heshidai.gold.console.module.sys.entity.Channel;

/**
 * 功能：渠道dao层
 *
 * @version 2016年8月5日下午2:45:45
 * @author baocheng.ren
 */
@Repository
public interface ChannelDAO extends BasicDAO<Channel> {
    /**
     * 功能：根据父id查询渠道
     *
     * @version 2016年12月28日下午6:08:44
     * @author baocheng.ren
     * @param parentId 父id
     * @return List<Channel>
     */
    public List<Channel> findByParentId(String parentId);
    
    /**
     * 功能：查询渠道名称是否已存在
     *
     * @version 2016年12月28日下午8:35:59
     * @author baocheng.ren
     * @param channelName 渠道名称
     * @param parentId 父id
     * @param channelId 主键
     * @return Channel
     */
    public Channel getByNameAndParentId(@Param("channelName") String channelName, @Param("parentId") String parentId,
            @Param("channelId") String channelId);
    
}
