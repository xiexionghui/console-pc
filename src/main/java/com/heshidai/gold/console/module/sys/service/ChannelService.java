package com.heshidai.gold.console.module.sys.service;

import java.util.List;

import com.heshidai.gold.console.module.sys.entity.Channel;

/**
 * 功能：渠道业务层
 *
 * @version 2016年8月5日下午2:47:06
 * @author baocheng.ren
 */
public interface ChannelService {
    
    /**
     * 功能：查询渠道树形列表
     *
     * @version 2016年12月28日下午6:11:32
     * @author baocheng.ren
     * @param channelId channelId
     * @return List<Channel>
     */
    public List<Channel> findListShu(String channelId);
    
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
    public Channel getByNameAndParentId(String channelName, String parentId, String channelId);
    
    /**
     * 功能：添加数据
     *
     * @version 2017年2月28日下午2:09:07
     * @author baocheng.ren
     * @param channel channel
     * @return int
     */
    public int insert(Channel channel);
    
    /**
     * 功能：跟进主键id修改数据
     *
     * @version 2017年2月28日下午2:08:51
     * @author baocheng.ren
     * @param channel channel
     * @return int
     */
    public int update(Channel channel);
    
    /**
     * 功能：根据主键id查询
     *
     * @version 2017年2月28日下午2:08:18
     * @author baocheng.ren
     * @param id 主键
     * @return Channel
     */
    public Channel findById(String id);
    
    /**
     * 功能：删除
     *
     * @version 2016年12月29日上午10:52:03
     * @author baocheng.ren
     * @param id 渠道id
     */
    public void delete(String id);
    
}
