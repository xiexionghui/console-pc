package com.heshidai.gold.console.module.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heshidai.gold.common.utils.DateUtil;
import com.heshidai.gold.console.common.util.StringUtils;
import com.heshidai.gold.console.module.sys.dao.ChannelDAO;
import com.heshidai.gold.console.module.sys.entity.Channel;
import com.heshidai.gold.console.module.sys.service.ChannelService;

/**
 * 功能：渠道业务层
 *
 * @version 2016年8月5日下午2:47:06
 * @author baocheng.ren
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    
    /**
     * 顶目录父id是0
     */
    private static final String PARENT_ID = "0";
    
    /**
     * 渠道dao
     */
    @Resource
    private ChannelDAO channelDAO;
    
    /**
     * 功能：查询渠道树形列表
     *
     * @version 2016年12月28日下午6:11:32
     * @author baocheng.ren
     * @param channelId channelId
     * @return List<Channel>
     */
    @Override
    public List<Channel> findListShu(String channelId) {
        List<Channel> channels = this.channelDAO.findByParentId(PARENT_ID);
        for (Channel channel : channels) {
            if (!channel.getId().equals(channelId)) {
                this.setSysOrganChildren(channel, channelId);
            }
        }
        return channels;
    }
    
    /**
     * 功能：循环设置渠道
     *
     * @version 2016年12月28日下午9:29:21
     * @author baocheng.ren
     * @param channelId channelId
     * @param info 输入列
     */
    private void setSysOrganChildren(Channel info, String channelId) {
        if (info == null || StringUtils.isBlank(info.getId())) {
            return;
        }
        List<Channel> children = this.channelDAO.findByParentId(info.getId());
        info.setChildren(children);
        for (Channel channel : children) {
            if (!channel.getId().equals(channelId)) {
                this.setSysOrganChildren(channel, channelId);
            }
        }
    }
    
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
    @Override
    public Channel getByNameAndParentId(String channelName, String parentId, String channelId) {
        return this.channelDAO.getByNameAndParentId(channelName, parentId, channelId);
    }
    
    /**
     * 功能：添加数据
     *
     * @version 2017年2月28日下午2:09:07
     * @author baocheng.ren
     * @param channel channel
     * @return int
     */
    @Override
    public int insert(Channel channel) {
        channel.setCreateTime(DateUtil.getCurrentDateTime());
        return this.channelDAO.insert(channel);
    }
    
    /**
     * 功能：跟进主键id修改数据
     *
     * @version 2017年2月28日下午2:08:51
     * @author baocheng.ren
     * @param channel channel
     * @return int
     */
    @Override
    public int update(Channel channel) {
        return this.channelDAO.update(channel);
    }
    
    /**
     * 功能：根据主键id查询
     *
     * @version 2017年2月28日下午2:08:18
     * @author baocheng.ren
     * @param id 主键
     * @return Channel
     */
    @Override
    public Channel findById(String id) {
        return this.channelDAO.findById(id);
    }
    
    /**
     * 功能：删除
     *
     * @version 2016年12月29日上午10:52:03
     * @author baocheng.ren
     * @param id 渠道id
     */
    @Override
    @Transactional
    public void delete(String id) {
        this.channelDAO.deleteById(id);
        List<Channel> channels = this.channelDAO.findByParentId(id);
        for (Channel channel : channels) {
            this.deleteSysOrganChildren(channel);
        }
    }
    
    /**
     * 功能：循环删除
     *
     * @version 2016年12月29日上午11:00:46
     * @author baocheng.ren
     * @param info 删除对象
     */
    private void deleteSysOrganChildren(Channel info) {
        if (info == null || StringUtils.isBlank(info.getId())) {
            return;
        }
        this.channelDAO.deleteById(info.getId());
        List<Channel> children = this.channelDAO.findByParentId(info.getId());
        for (Channel channel : children) {
            this.deleteSysOrganChildren(channel);
        }
    }
    
}
