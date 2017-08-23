package com.heshidai.gold.console.module.sys.service;

import com.heshidai.gold.console.module.sys.entity.FollowRecord;
import com.heshidai.gold.console.module.sys.entity.page.FollowRecordPage;

/**
 * 功能：跟进记录服务类
 *
 * @version 2016年8月5日下午2:47:06
 * @author baocheng.ren
 */
public interface FollowRecordService {
    
    /**
     * 功能：保存跟进记录
     *
     * @version 2017年1月19日上午10:25:43
     * @author baocheng.ren
     * @param followRecord followRecord
     * @return int
     */
    public int insert(FollowRecord followRecord);
    
    /**
     * 功能：查询跟进记录列表
     *
     * @version 2017年1月7日上午11:32:23
     * @author baocheng.ren
     * @param page page
     */
    public void findList(FollowRecordPage page);
    
    /**
     * 功能：根据主键id查询数据
     *
     * @version 2017年2月22日上午10:11:24
     * @author baocheng.ren
     * @param id id
     * @return FollowRecord
     */
    public FollowRecord findById(String id);
}
