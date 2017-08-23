/******************************************************************************
 * Copyright (C) 2014 ShenZhen HeShiDai andy.zl Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.heshidai.gold.console.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 
 * Excel导出工具类
 * 
 * @version 2017年2月5日下午1:39:50
 * @author dx.love
 */
public abstract class ExcelUtil {
    
    private static final int NUMBER_10 = 10;
    private static final int NUMBER_100 = 100;
    
    /**
     * 导出excel
     * 
     * @param response HttpServletResponse响应对象
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @throws IOException 异常
     */
    public static void exportExcel(HttpServletResponse response, List<Object> dataList, String fileName, String[] keys,
            String[] cellTitles) throws IOException {
        exportExcelForReplace(response, dataList, fileName, keys, cellTitles, null);
    }
    
    /**
     * 导出excel，增加汇总行
     * 
     * @param response HttpServletResponse响应对象
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @param sumColumn 需要在excel结尾加上统计行的列和公式，比如(1,"sum(B2,B10000)")表示第2列第2行到第10000行的和，列数从0开始
     * @throws IOException 异常
     */
    public static void exportExcel(HttpServletResponse response, List<Object> dataList, String fileName, String[] keys,
            String[] cellTitles, Map<Integer, String> sumColumn) throws IOException {
        exportExcelAddRow(response, dataList, fileName, keys, cellTitles, null, sumColumn, null, null);
    }
    
    /**
     * 导出excel，替换值和增加汇总行
     * 
     * @param response HttpServletResponse响应对象
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @param replaceMap 替换值的map,第一层map的key为要替换的字段名，value中的map则为原值与替换值的对应关系。 不用可传此参数为null或调用exportExcel方法
     * @param sumColumn 需要在excel结尾加上统计行的列和公式，比如(1,"sum(B2,B10000)")表示第2列第2行到第10000行的和，列数从0开始
     * @throws IOException 异常
     */
    public static void exportExcel(HttpServletResponse response, List<Object> dataList, String fileName, String[] keys,
            String[] cellTitles, Map<String, Map<String, String>> replaceMap, Map<Integer, String> sumColumn)
            throws IOException {
        exportExcelAddRow(response, dataList, fileName, keys, cellTitles, replaceMap, sumColumn, null, null);
    }
    
    /**
     * 导出excel，替换值、增加汇总行、增加行
     * 
     * @param response HttpServletResponse响应对象
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @param replaceMap 替换值的map,第一层map的key为要替换的字段名，value中的map则为原值与替换值的对应关系。 不用可传此参数为null或调用exportExcel方法
     * @param sumColumn 需要在excel结尾加上统计行的列和公式，比如(1,"sum(B2,B10000)")表示第2列第2行到第10000行的和，列数从0开始
     * @param addRows 需要在excel结尾加上的行
     * @throws IOException 异常
     */
    public static void exportExcel(HttpServletResponse response, List<Object> dataList, String fileName, String[] keys,
            String[] cellTitles, Map<String, Map<String, String>> replaceMap, Map<Integer, String> sumColumn,
            List<String[]> addRows) throws IOException {
        exportExcelAddRow(response, dataList, fileName, keys, cellTitles, replaceMap, sumColumn, addRows, null);
    }
    
    /**
     * 导出excel需要替换值的
     * 
     * @param response HttpServletResponse响应对象
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @param replaceMap 替换值的map,第一层map的key为要替换的字段名，value中的map则为原值与替换值的对应关系。 不用可传此参数为null或调用exportExcel方法
     * @throws IOException
     */
    public static void exportExcelForReplace(HttpServletResponse response, List<Object> dataList, String fileName,
            String[] keys, String[] cellTitles, Map<String, Map<String, String>> replaceMap) throws IOException {
        exportExcelAddRow(response, dataList, fileName, keys, cellTitles, replaceMap, null, null, null);
    }
    
    /**
     * 功能：
     *
     * @version 2017年2月23日上午10:25:48
     * @author baocheng.ren
     * @param response response
     * @param dataList 数据列表,列表中的项支持实体对象类型或Map类型
     * @param fileName 导出Excel的下载文件名称
     * @param keys 数据的属性名称:可以是map的key值、或对象的属性名称
     * @param cellTitles 第一行的列标题，不用标题的话可传值为null
     * @param replaceMap 替换值的map,第一层map的key为要替换的字段名，value中的map则为原值与替换值的对应关系。 不用可传此参数为null或调用exportExcel方法
     * @param sumColumn 需要在excel结尾加上统计行的列和公式，比如(1,"sum(B2,B10000)")表示第2列第2行到第10000行的和，列数从0开始
     * @param addRows 需要在excel结尾加上的行
     * @param columnWidths columnWidths
     * @throws IOException IOException
     */
    public static void exportExcelAddRow(HttpServletResponse response, List<Object> dataList, String fileName,
            String[] keys, String[] cellTitles, Map<String, Map<String, String>> replaceMap,
            Map<Integer, String> sumColumn, List<String[]> addRows, int[] columnWidths) throws IOException {
        if (dataList.size() > 0) {
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\""
                    + new String(fileName.getBytes(), "ISO8859-1") + "\"");
            OutputStream os = response.getOutputStream();
            // 把XSSFWorkbook升级为SXSSFWorkbook，通过设置内存中的行数上限，超过这个上限的行就把它刷到硬盘，减少内存占用
            SXSSFWorkbook outWb = new SXSSFWorkbook(NUMBER_100);
            Sheet outSheet = outWb.createSheet("sheet1");
            Font columnFont = outWb.createFont();
            CellStyle columnStyle = outWb.createCellStyle();
            columnFont.setFontName("宋体");
            columnFont.setFontHeightInPoints((short) NUMBER_10);
            columnStyle.setFont(columnFont);
            // 左右居中
            columnStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // 上下居中
            columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            int rowIndex = 0;
            if (cellTitles != null) {
                addArrayToRow(outSheet, 0, cellTitles, columnStyle, columnWidths);
                rowIndex = 1;
            }
            for (int k = 0; k < dataList.size(); k++, rowIndex++) {
                addDataToRow(outSheet, rowIndex, dataList.get(k), keys, replaceMap, columnStyle, columnWidths);
            }
            if (sumColumn != null) {
                addSummary(outSheet, rowIndex, sumColumn, columnStyle, columnWidths);
            }
            if (addRows != null && addRows.size() > 0) {
                for (String[] arrayRow : addRows) {
                    addArrayToRow(outSheet, rowIndex, arrayRow, columnStyle, columnWidths);
                    rowIndex++;
                }
            }
            outWb.write(os);
            os.close();
            // 解决临时文件重新恢复工作薄到硬盘
            outWb.dispose();
        }
        else {
            // 显示无数据页面
            response.setContentType("text/html;charset=utf-8");
            response.getOutputStream().write("无数据显示!".getBytes("utf-8"));
        }
    }
    
    /**
     * 功能：从Map初始化为Excel行Row
     *
     * @version 2017年2月23日上午10:21:22
     * @author baocheng.ren
     * @param sheet sheet
     * @param index index
     * @param data data
     * @param keys keys
     * @param replaceMap replaceMap
     * @param columnStyle columnStyle
     * @param columnWidths columnWidths
     */
    private static void addDataToRow(Sheet sheet, int index, Object data, String[] keys,
            Map<String, Map<String, String>> replaceMap, CellStyle columnStyle, int[] columnWidths) {
        Row row = sheet.createRow(index);
        if (data.getClass().isAssignableFrom(Map.class)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> dataMap = (Map<String, Object>) data;
            
            for (int i = 0; i < keys.length; i++) {
                Cell cell = row.createCell(i);
                Object obj = dataMap.get(keys[i]);
                // 如果是数值类型，把所在的单元格的格式设置为数值型
                if (obj instanceof BigDecimal || obj instanceof Double 
                        || obj instanceof Integer || obj instanceof Long) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    if (obj != null) {
                        cell.setCellValue(Double.parseDouble(obj.toString()));
                    }
                    else {
                        cell.setCellValue(0);
                    }
                }
                else {
                    String value = StringUtils.valueOfReplaceNull(obj);
                    // 值替换操作
                    value = replaceValue(value, keys[i], replaceMap);
                    cell.setCellValue(value);
                }
                if (columnWidths != null) {
                    sheet.setColumnWidth(i, columnWidths[i]);
                }
                cell.setCellStyle(columnStyle);
            }
        }
        else {
            for (int i = 0; i < keys.length; i++) {
                Cell cell = row.createCell(i);
                Object obj = ReflectUtil.invokeGet(data, keys[i]);
                // 如果是数值类型，把所在的单元格的格式设置为数值型
                if (obj instanceof BigDecimal || obj instanceof Double 
                        || obj instanceof Integer || obj instanceof Long) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    if (obj != null) {
                        cell.setCellValue(Double.parseDouble(obj.toString()));
                    }
                    else {
                        cell.setCellValue(0);
                    }
                }
                else {
                    String value = StringUtils.valueOfReplaceNull(obj);
                    value = replaceValue(value, keys[i], replaceMap);
                    cell.setCellValue(value);
                }
                if (columnWidths != null) {
                    sheet.setColumnWidth(i, columnWidths[i]);
                }
                cell.setCellStyle(columnStyle);
            }
        }
        
    }
    
    /**
     * 功能：转换数组为excel中的行
     *
     * @version 2017年2月23日上午10:22:04
     * @author baocheng.ren
     * @param sheet sheet
     * @param index index
     * @param keys keys
     * @param columnStyle columnStyle
     * @param columnWidths columnWidths
     */
    private static void addArrayToRow(Sheet sheet, int index, String[] keys, 
            CellStyle columnStyle, int[] columnWidths) {
        Row row = sheet.createRow(index);
        for (int i = 0; i < keys.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(keys[i]);
            cell.setCellStyle(columnStyle);
            if (columnWidths != null) {
                sheet.setColumnWidth(i, columnWidths[i]);
            }
        }
    }
    
    /**
     * 功能：值替换，如状态数字1,2,3转换为对应状态的文本或其他特殊值替换处理
     *
     * @version 2017年2月23日上午10:22:31
     * @author baocheng.ren
     * @param value value
     * @param key key
     * @param replaceMap replaceMap
     * @return String
     */
    private static String replaceValue(String value, String key, Map<String, Map<String, String>> replaceMap) {
        // 值替换操作
        if (replaceMap == null) {
            return value;
        }
        Map<String, String> map = replaceMap.get(key);
        if (map == null) {
            return value;
        }
        String replaceValue = map.get(value);
        if (replaceValue != null) {
            return replaceValue;
        }
        return value;
    }
    
    /**
     * 
     * 增加统计行
     * 
     * @version 2016年7月26日下午2:27:36
     * @author wenbin.zhu
     * @param sheet 写入的工作表
     * @param index 写入的行数
     * @param sumColumn 统计的列和公式，比如(1,"sum(B2,B10000)")表示第2列第2行到第10000行的和，列数从0开始
     * @param columnStyle 列样式
     * @param columnWidths 列宽度
     */
    private static void addSummary(Sheet sheet, int index, Map<Integer, String> sumColumn, CellStyle columnStyle,
            int[] columnWidths) {
        Row row = sheet.createRow(index);
        Cell totalCell = row.createCell(0);
        totalCell.setCellValue("统计");
        Iterator<Integer> it = sumColumn.keySet().iterator();
        while (it.hasNext()) {
            int columnIndex = it.next();
            Cell cell = row.createCell(columnIndex);
            cell.setCellType(Cell.CELL_TYPE_FORMULA);
            cell.setCellFormula(sumColumn.get(columnIndex));
            cell.setCellStyle(columnStyle);
            if (columnWidths != null) {
                sheet.setColumnWidth(columnIndex, columnWidths[columnIndex]);
            }
        }
    }
}
