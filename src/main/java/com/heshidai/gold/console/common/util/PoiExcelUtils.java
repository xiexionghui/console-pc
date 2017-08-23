/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel工具类
 * 
 * @version 2017年2月16日下午4:19:52
 * @author baocheng.ren
 */
public class PoiExcelUtils {
    
    /** 数字格式化 */
    private static NumberFormat FORMAT = NumberFormat.getInstance();
    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(PoiExcelUtils.class);
    /** 列默认宽度 */
    @SuppressWarnings("unused")
    private static final int DEFAUL_COLUMN_WIDTH = 4000;
    
    /**
     * 1.创建 workbook
     * 
     * @return {@link HSSFWorkbook}
     * @Author : ll. create at 2016年4月14日 上午9:28:27
     */
    @SuppressWarnings("unused")
    private HSSFWorkbook getHSSFWorkbook() {
        LOGGER.info("【创建 workbook】");
        return new HSSFWorkbook();
    }
    
    /**
     * 2.创建 sheet
     * 
     * @param hssfWorkbook {@link HSSFWorkbook}
     * @param sheetName sheet 名称
     * @return {@link HSSFSheet}
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    @SuppressWarnings("unused")
    private HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName) {
        LOGGER.info("【创建 sheet】sheetName ： " + sheetName);
        return hssfWorkbook.createSheet(sheetName);
    }
    
    /**
     * 根据文件路径读取excel文件，默认读取第0个sheet
     * 
     * @param excelPath excel的路径
     * @param skipRows 需要跳过的行数
     * @param columnCount 列数量
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(String excelPath, int skipRows, int columnCount) throws Exception {
        return readExcel(excelPath, skipRows, columnCount, 0, null);
    }
    
    /**
     * 根据文件路径读取excel文件的指定sheet
     * 
     * @param excelPath excel的路径
     * @param skipRows 需要跳过的行数
     * @param columnCount 列数量
     * @param sheetNo 要读取的sheet的索引，从0开始
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(String excelPath, int skipRows, int columnCount, int sheetNo)
            throws Exception {
        return readExcel(excelPath, skipRows, columnCount, sheetNo, null);
    }
    
    /**
     * 根据文件路径读取excel文件的指定sheet，并封装空值单位各的坐标，默认读取第0个sheet
     * 
     * @param excelPath excel的路径
     * @param skipRows 需要跳过的行数
     * @param columnCount 列数量
     * @param noneCellValuePositionList 存储空值的单元格的坐标，每个坐标以x-y的形式拼接，如2-5表示第二行第五列
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(String excelPath, int skipRows, int columnCount,
            List<String> noneCellValuePositionList) throws Exception {
        return readExcel(excelPath, skipRows, columnCount, 0, noneCellValuePositionList);
    }
    
    /**
     * 根据文件路径读取excel文件的指定sheet，并封装空值单位各的坐标，默认读取第0个sheet
     * 
     * @param excelPath excel的路径
     * @param skipRows 需要跳过的行数
     * @param columnCount 列数量
     * @param columnNumberForSkipValueValidateSet 不需要做空值验证的列的索引集合
     * @param noneCellValuePositionList 存储空值的单元格的坐标，每个坐标以x-y的形式拼接，如2-5表示第二行第五列
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(String excelPath, int skipRows, int columnCount,
            Set<Integer> columnNumberForSkipValueValidateSet, List<String> noneCellValuePositionList) throws Exception {
        PoiExcelUtilsReadExcel poiExcelUtilsReadExcel = new PoiExcelUtilsReadExcel();
        poiExcelUtilsReadExcel.setColumnCount(columnCount);
        poiExcelUtilsReadExcel.setColumnNumberForSkipValueValidateSet(columnNumberForSkipValueValidateSet);
        poiExcelUtilsReadExcel.setExcelPath(excelPath);
        poiExcelUtilsReadExcel.setNoneCellValuePositionList(noneCellValuePositionList);
        poiExcelUtilsReadExcel.setSheetNo(0);
        poiExcelUtilsReadExcel.setSkipRows(skipRows);
        return readExcel(poiExcelUtilsReadExcel);
    }
    
    /**
     * 根据文件路径读取excel文件的指定sheet，并封装空值单位各的坐标
     * 
     * @param excelPath excel的路径
     * @param skipRows 需要跳过的行数
     * @param columnCount 列数量
     * @param sheetNo 要读取的sheet的索引，从0开始
     * @param noneCellValuePositionList 存储空值的单元格的坐标，每个坐标以x-y的形式拼接，如2-5表示第二行第五列
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(String excelPath, int skipRows, int columnCount, int sheetNo,
            List<String> noneCellValuePositionList) throws Exception {
        PoiExcelUtilsReadExcel poiExcelUtilsReadExcel = new PoiExcelUtilsReadExcel();
        poiExcelUtilsReadExcel.setColumnCount(columnCount);
        poiExcelUtilsReadExcel.setColumnNumberForSkipValueValidateSet(null);
        poiExcelUtilsReadExcel.setExcelPath(excelPath);
        poiExcelUtilsReadExcel.setNoneCellValuePositionList(noneCellValuePositionList);
        poiExcelUtilsReadExcel.setSheetNo(0);
        poiExcelUtilsReadExcel.setSkipRows(skipRows);
        return readExcel(poiExcelUtilsReadExcel);
    }
    
    /**
     * 根据文件路径读取excel文件的指定sheet，并封装空值单位各的坐标
     * 
     * @param poiExcelUtilsReadExcel poiExcelUtilsReadExcel
     * @return List<String[]> 集合中每一个元素是一个数组，按单元格索引存储每个单元格的值，一个元素可以封装成一个需要的java bean
     * @throws Exception Exception
     * @Author : ll. create at 2016年4月14日 上午9:28:39
     */
    public static List<String[]> readExcel(PoiExcelUtilsReadExcel poiExcelUtilsReadExcel) throws Exception {
        LOGGER.info("【读取Excel】excelPath = {} , skipRows = {} ", 
                poiExcelUtilsReadExcel.getExcelPath(), poiExcelUtilsReadExcel.getSkipRows());
        LOGGER.info(" columnCount = {} , columnNumberForSkipValueValidateSet = {}", 
                poiExcelUtilsReadExcel.getColumnCount(), 
                poiExcelUtilsReadExcel.getColumnNumberForSkipValueValidateSet());
        
        if (StringUtils.isBlank(poiExcelUtilsReadExcel.getExcelPath())) {
            LOGGER.warn("【参数excelPath为空】");
            return new ArrayList<String[]>();
        }
        
        FileInputStream is = new FileInputStream(new File(poiExcelUtilsReadExcel.getExcelPath()));
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        List<String[]> list = new ArrayList<String[]>();
        HSSFSheet sheet = wb.getSheetAt(poiExcelUtilsReadExcel.getSheetNo());
        // 得到总共的行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        try {
            for (int i = poiExcelUtilsReadExcel.getSkipRows(); i < rowNum; i++) {
                String[] vals = new String[poiExcelUtilsReadExcel.getColumnCount()];
                HSSFRow row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                for (int j = 0; j < poiExcelUtilsReadExcel.getColumnCount(); j++) {
                    HSSFCell cell = row.getCell(j);
                    String val = getStringCellValue(cell);
                    
                    // 没有需要跳过校验的列索引
                    if (CollectionUtils.isEmpty(poiExcelUtilsReadExcel.getColumnNumberForSkipValueValidateSet())) {
                        if (poiExcelUtilsReadExcel.getNoneCellValuePositionList() != null && StringUtils.isBlank(val)) {
                            // 封装空值单元格的坐标
                            poiExcelUtilsReadExcel.getNoneCellValuePositionList().add((i + 1) + "-" + j);
                        }
                    }
                    else {
                        // 如果需要校验空值的单元格、当前列索引不在需要跳过校验的索引集合中
                        if (poiExcelUtilsReadExcel.getNoneCellValuePositionList() != null && StringUtils.isBlank(val)
                                && !poiExcelUtilsReadExcel.getColumnNumberForSkipValueValidateSet().contains(j)) {
                            // 封装空值单元格的坐标
                            poiExcelUtilsReadExcel.getNoneCellValuePositionList().add((i + 1) + "-" + j);
                        }
                    }
                    
                    vals[j] = val;
                }
                list.add(vals);
            }
        }
        catch (Exception e) {
            LOGGER.error("【Excel解析失败】", e);
            throw new RuntimeException("Excel解析失败");
        }
        return list;
    }
    
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格{@link HSSFCell}
     * @return 单元格数据内容（可能是布尔类型等，强制转换成String）
     * @Author : ll. create at 2016年4月14日 上午9:53:07
     */
    private static String getStringCellValue(HSSFCell cell) {
        if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(FORMAT.format(cell.getNumericCellValue())).replace(",", "");
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (StringUtils.isBlank(strCell)) {
            return "";
        }
        
        return strCell;
    }
    
}
