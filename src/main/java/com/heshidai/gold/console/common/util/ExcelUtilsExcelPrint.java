/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.util;

import java.util.List;
import java.util.Map;

/**
 * 功能：导出打印类
 *
 * @version 2017年2月16日下午4:48:10
 * @author baocheng.ren
 */
public class ExcelUtilsExcelPrint {
    
/*    String[] fieldNames,
    
    String[][] secondHeadNames,
    String[] fileds, */
    
    /**
     * 标题名称
     */
    private String headName;
    
    /**
     * 列标题名称
     */
    private String[] titles;
    
    /**
     * 数据
     */
    private List<Map<String, Object>> dataList;
    
    /**
     * 列格式
     */
    private Map<String, HSSFWordBookStyle> styleMap;
    
    /**
     * 列宽度
     */
    private int[] columnWidth;

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public Map<String, HSSFWordBookStyle> getStyleMap() {
        return styleMap;
    }

    public void setStyleMap(Map<String, HSSFWordBookStyle> styleMap) {
        this.styleMap = styleMap;
    }

    public int[] getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int[] columnWidth) {
        this.columnWidth = columnWidth;
    }
    
}
