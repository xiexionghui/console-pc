package com.heshidai.gold.console.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.heshidai.gold.console.common.constant.Constants;

/**
 * @description(描述): excel工具方法
 * @author(创建人): zhoujun
 * @date(创建日期): 2013-12-27
 * @version(版本): v1.0
 * @company(公司): heshidai.com
 * @copyright(c): 合时代@copyright by heshidai.com
 * @history(修改历史):
 * @version 2017年2月16日下午4:25:09
 * @author baocheng.ren
 */
public class ExcelUtils {
    
    private static final int NUMBER_10 = 10;
    private static final int NUMBER_11 = 11;
    private static final int NUMBER_12 = 12;
    private static final int NUMBER_350 = 350;
    private static final int NUMBER_400 = 400;
    private static final int NUMBER_450 = 450;
    
    /**
     * 读取Excel文件
     * 
     * @param file 要读取的Excel文件
     * @param fileName fileName
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    public static List<List<String>> readExcel(File file, String fileName, int ignoreRows) throws IOException {
        String suffix = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(suffix)) {
            return read2003Excel(file, ignoreRows);
        }
        else if ("xlsx".equals(suffix)) {
            return read2007Excel(file, ignoreRows);
        }
        else {
            throw new IOException("文件类型错误！");
        }
    }
    
    /**
     * 读取Excel文件,文件内容就读取什么内容，比如金额格式
     * 
     * @param file 要读取的Excel文件
     * @param fileName fileName
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    public static List<List<String>> readByExcel(File file, String fileName, int ignoreRows) throws IOException {
        String suffix = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(suffix)) {
            return readBy2003Excel(file, ignoreRows);
        }
        else if ("xlsx".equals(suffix)) {
            return readBy2007Excel(file, ignoreRows);
        }
        else {
            throw new IOException("文件类型错误！");
        }
    }
    
    /**
     * 读取2003版本Excel
     * 
     * @param file 要读取的Excel文件
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    private static List<List<String>> readBy2003Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    HSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                else {
                                    value = "";
                                }
                            }
                            else {
                                value = new DecimalFormat("0").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            }
                            else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            if (cell.getBooleanCellValue()) {
                                value = "Y";
                            }
                            else {
                                value = "N";
                            }
                            break;
                        default:
                            value = "";
                            break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }
    
    /**
     * 读取2007版本Excel
     * 
     * @param file 要读取的Excel文件
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    private static List<List<String>> readBy2007Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    XSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                else {
                                    value = "";
                                }
                            }
                            else {
                                value = new DecimalFormat("0").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            }
                            else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            if (cell.getBooleanCellValue()) {
                                value = "Y";
                            }
                            else {
                                value = "N";
                            }
                            break;
                        default:
                            value = "";
                            break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }
    
    /**
     * 读取2003版本Excel
     * 
     * @param file 要读取的Excel文件
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    private static List<List<String>> read2003Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    HSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                else {
                                    value = "";
                                }
                            }
                            else {
                                value = new DecimalFormat("0").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            }
                            else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            if (cell.getBooleanCellValue()) {
                                value = "Y";
                            }
                            else {
                                value = "N";
                            }
                            break;
                        default:
                            value = "";
                            break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }
    
    /**
     * 读取2007版本Excel
     * 
     * @param file 要读取的Excel文件
     * @param ignoreRows 想要忽略的表头行数
     * @return 外层集合是所有行，内层集合是所有列
     * @throws IOException IOException
     */
    private static List<List<String>> read2007Excel(File file, int ignoreRows) throws IOException {
        // 构造一个Excel对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        // 循环读取所有的sheet页面
        List<List<String>> rows = new ArrayList<List<String>>();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            // 获取一个sheet页面
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            // 循环读取sheet中所有的行
            for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // 获取一行
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                // 循环读取一行中所有的单元格
                List<String> cells = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                    // 获取一个单元格
                    XSSFCell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    String value;
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                else {
                                    value = "";
                                }
                            }
                            else {
                                value = new DecimalFormat("0").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            }
                            else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            if (cell.getBooleanCellValue()) {
                                value = "Y";
                            }
                            else {
                                value = "N";
                            }
                            break;
                        default:
                            value = "";
                            break;
                    }
                    cells.add(value.trim());
                }
                rows.add(cells);
            }
        }
        return rows;
    }
    
    /**
     * 功能：导出
     *
     * @version 2017年2月16日下午5:04:14
     * @author baocheng.ren
     * @param excelUtilsExcelPrint excelUtilsExcelPrint
     * @param workbook workbook
     * @param fieldNames 获取列数据的map的id
     * @return HSSFWorkbook
     * @throws Exception Exception
     */
    public static HSSFWorkbook excelPrint(ExcelUtilsExcelPrint excelUtilsExcelPrint, 
            HSSFWorkbook workbook, String[] fieldNames)
            throws Exception {
        if (StringUtils.isBlank(excelUtilsExcelPrint.getHeadName())) {
            excelUtilsExcelPrint.setHeadName("无标题");
        }
        String sheetCount = "";
        if (excelUtilsExcelPrint.getDataList() != null 
                && (excelUtilsExcelPrint.getDataList().size() + 2) > Constants.EXPOT_EXCEL_MAX_PAGE_SIZE) {
            sheetCount = "_1";
        }
        
        // 创建一个Excel的Sheet
        HSSFSheet sheet = workbook.createSheet(excelUtilsExcelPrint.getHeadName() + sheetCount);
        if (excelUtilsExcelPrint.getTitles() == null || excelUtilsExcelPrint.getTitles().length == 0) {
            return workbook;
        }
        
        /**
         * 设置列宽
         */
        if (excelUtilsExcelPrint.getColumnWidth() != null && excelUtilsExcelPrint.getColumnWidth().length > 0) {
            for (int i = 0; i < excelUtilsExcelPrint.getColumnWidth().length; i++) {
                sheet.setColumnWidth(i, excelUtilsExcelPrint.getColumnWidth()[i]);
            }
        }
        
        /**
         * 设置普通样式
         */
        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setFontHeightInPoints((short) NUMBER_10);
        columnStyle.setFont(columnFont);
        // 左右居中
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        try {
            int rowSize = 0;
            /**
             * 创建标题
             */
            createTitle(excelUtilsExcelPrint.getHeadName(), workbook, sheet, excelUtilsExcelPrint.getTitles());
            rowSize = 2;
            /**
             * 列表内容
             */
            if (excelUtilsExcelPrint.getDataList() != null && excelUtilsExcelPrint.getDataList().size() > 0 
                    && fieldNames != null && fieldNames.length > 0) {
                for (int i = 0; i < excelUtilsExcelPrint.getDataList().size(); i++) {
                    if (rowSize == Constants.EXPOT_EXCEL_MAX_PAGE_SIZE) {
                        sheet = getNewSheet(workbook, excelUtilsExcelPrint.getColumnWidth(), 
                                excelUtilsExcelPrint.getTitles(), excelUtilsExcelPrint.getHeadName() + "_2");
                        createTitle(excelUtilsExcelPrint.getHeadName(), workbook, sheet, 
                                excelUtilsExcelPrint.getTitles());
                        rowSize = 2;
                    }
                    Map<String, Object> data = excelUtilsExcelPrint.getDataList().get(i);
                    HSSFRow row = sheet.createRow(rowSize);
                    rowSize += 1;
                    row.setHeight((short) NUMBER_350);
                    for (int j = 0; j < fieldNames.length; j++) {
                        HSSFCell cell = row.createCell(j);
                        String value = data.get(fieldNames[j]) == null ? "" : String.valueOf(data.get(fieldNames[j]));
                        cell.setCellValue(new HSSFRichTextString(value));
                        cell.setCellStyle(columnStyle);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return workbook;
        
    }
    
    /**
     * 功能：导出 by heqing
     *
     * @version 2017年2月16日下午5:06:07
     * @author baocheng.ren
     * @param excelUtilsExcelPrint excelUtilsExcelPrint
     * @param workbook workbook
     * @param secondHeadNames 二级标题，[标题名，长度]
     * @param fileds 获取列数据的map的id
     * @return HSSFWorkbook
     * @throws Exception Exception
     */
    public static HSSFWorkbook excelPrint(ExcelUtilsExcelPrint excelUtilsExcelPrint, HSSFWorkbook workbook, 
            String[][] secondHeadNames, String[] fileds) throws Exception {
        
        // 创建一个Excel的Sheet
        HSSFSheet sheet = workbook.createSheet(excelUtilsExcelPrint.getHeadName());
        if (excelUtilsExcelPrint.getTitles() == null || excelUtilsExcelPrint.getTitles().length == 0) {
            return workbook;
        }
        
        /**
         * 设置列宽
         */
        if (excelUtilsExcelPrint.getColumnWidth() != null && excelUtilsExcelPrint.getColumnWidth().length > 0) {
            for (int i = 0; i < excelUtilsExcelPrint.getColumnWidth().length; i++) {
                sheet.setColumnWidth(i, excelUtilsExcelPrint.getColumnWidth()[i]);
            }
        }
        
        /**
         * 设置普通样式
         */
        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setFontHeightInPoints((short) NUMBER_10);
        columnStyle.setFont(columnFont);
        // 左右居中
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        try {
            int rowSize = 0;
            
            /**
             * 创建标题
             */
            /**
             * 设置标题样式
             */
            HSSFFont columnHeadFont = workbook.createFont();
            columnHeadFont.setFontName("宋体");
            columnHeadFont.setFontHeightInPoints((short) NUMBER_12);
            columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            
            HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
            columnHeadStyle.setFont(columnHeadFont);
            // 左右居中
            columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 上下居中
            columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            columnHeadStyle.setLocked(true);
            columnHeadStyle.setWrapText(true);
            if (StringUtils.isNotEmpty(excelUtilsExcelPrint.getHeadName())) {
                /**
                 * 创建第一行标题
                 */
                HSSFRow headRow = sheet.createRow(rowSize);
                rowSize += 1;
                headRow.setHeight((short) NUMBER_450);
                HSSFCell headCell = headRow.createCell(0);
                headCell.setCellValue(new HSSFRichTextString(excelUtilsExcelPrint.getHeadName()));
                headCell.setCellStyle(columnHeadStyle);
                
                /**
                 * 合并单元格 参数： 起始行数，终止行数，起始列数，终止列数
                 */
                // 指定合并区域
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelUtilsExcelPrint.getTitles().length - 1));
            }
            
            HSSFFont titleFont = workbook.createFont();
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleFont.setFontName("宋体");
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleFont.setFontHeightInPoints((short) NUMBER_11);
            titleStyle.setFont(titleFont);
            // 左右居中
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 上下居中
            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            /**
             * 创建二级列名
             */
            HSSFRow secondHeadRow = sheet.createRow(rowSize);
            secondHeadRow.setHeight((short) NUMBER_450);
            int cellNumTotal = 0;
            for (int i = 0; secondHeadNames != null && i < secondHeadNames.length; i++) {
                String titleStr = secondHeadNames[i][0];
                int cellNum = Integer.parseInt(secondHeadNames[i][1]);
                HSSFCell cell = secondHeadRow.createCell(cellNumTotal);
                cellNumTotal += cellNum;
                cell.setCellValue(new HSSFRichTextString(titleStr));
                cell.setCellStyle(titleStyle);
            }
            cellNumTotal = 0;
            for (int i = 0; secondHeadNames != null && i < secondHeadNames.length; i++) {
                int cellNum = Integer.parseInt(secondHeadNames[i][1]);
                sheet.addMergedRegion(new CellRangeAddress(rowSize, rowSize, cellNumTotal, cellNumTotal + cellNum - 1));
                cellNumTotal += cellNum;
            }
            rowSize += 1;
            /**
             * 创建列名
             */
            HSSFRow titleRow = sheet.createRow(rowSize);
            rowSize += 1;
            titleRow.setHeight((short) NUMBER_400);
            for (int i = 0; i < excelUtilsExcelPrint.getTitles().length; i++) {
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(new HSSFRichTextString(excelUtilsExcelPrint.getTitles()[i]));
                cell.setCellStyle(titleStyle);
            }
            
            /**
             * 列表内容
             */
            if (excelUtilsExcelPrint.getDataList() != null 
                    && excelUtilsExcelPrint.getDataList().size() > 0 
                    && fileds != null && fileds.length > 0) {
                for (int i = 0; i < excelUtilsExcelPrint.getDataList().size(); i++) {
                    Map<String, Object> data = excelUtilsExcelPrint.getDataList().get(i);
                    HSSFRow row = sheet.createRow(rowSize);
                    rowSize += 1;
                    row.setHeight((short) NUMBER_350);
                    for (int j = 0; j < fileds.length; j++) {
                        HSSFCell cell = row.createCell(j);
                        String value = data.get(fileds[j]) == null ? "" : String.valueOf(data.get(fileds[j]));
                        cell.setCellValue(new HSSFRichTextString(value));
                        cell.setCellStyle(columnStyle);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return workbook;
        
    }
    
    /**
     * 功能：
     *
     * @version 2017年2月16日下午4:29:22
     * @author baocheng.ren
     * @param headName headName
     * @param workbook workbook
     * @param sheet sheet
     * @param titles titles
     */
    private static void createTitle(String headName, HSSFWorkbook workbook, HSSFSheet sheet, String[] titles) {
        int rowSize = 0;
        /**
         * 创建标题
         */
        /**
         * 设置标题样式
         */
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) NUMBER_12);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        // 左右居中
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        
        /**
         * 创建第一行标题
         */
        HSSFRow headRow = sheet.createRow(rowSize);
        rowSize += 1;
        headRow.setHeight((short) NUMBER_450);
        HSSFCell headCell = headRow.createCell(0);
        headCell.setCellValue(new HSSFRichTextString(headName));
        headCell.setCellStyle(columnHeadStyle);
        
        /**
         * 合并单元格 参数： 起始行数，终止行数，起始列数，终止列数
         */
        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titles.length - 1));
        
        /**
         * 创建列名
         */
        HSSFRow titleRow = sheet.createRow(rowSize);
        rowSize += 1;
        titleRow.setHeight((short) NUMBER_400);
        
        HSSFFont columnFont = workbook.createFont();
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        columnFont.setFontName("宋体");
        columnFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        columnFont.setFontHeightInPoints((short) NUMBER_11);
        columnStyle.setFont(columnFont);
        // 左右居中
        columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(new HSSFRichTextString(titles[i]));
            cell.setCellStyle(columnStyle);
        }
    }
    
    /**
     * 功能：
     *
     * @version 2017年2月16日下午4:24:23
     * @author baocheng.ren
     * @param workbook workbook
     * @param columnWidth columnWidth
     * @param titles titles
     * @param headName headName
     * @return HSSFSheet
     */
    private static HSSFSheet getNewSheet(HSSFWorkbook workbook, int[] columnWidth, String[] titles, String headName) {
        // 创建一个Excel的Sheet
        HSSFSheet sheet = workbook.createSheet(headName);
        /**
         * 设置列宽
         */
        if (columnWidth != null && columnWidth.length > 0) {
            for (int i = 0; i < columnWidth.length; i++) {
                sheet.setColumnWidth(i, columnWidth[i]);
            }
        }
        return sheet;
    }
    
    /**
     * 根据文件名判断文件是否是Excel
     * 
     * @param filename filename
     * @return boolean
     */
    public static boolean isExcel(String filename) {
        if (filename == null) {
            return false;
        }
        String suffix = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
        if ("xls".equals(suffix) || "xlsx".equals(suffix)) {
            return true;
        }
        return false;
    }
    
}
