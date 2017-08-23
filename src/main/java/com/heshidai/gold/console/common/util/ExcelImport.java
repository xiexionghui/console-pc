/******************************************************************************
 * Copyright (C) 2014 ShenZhen HeShiDai andy.zl Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.heshidai.gold.console.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 功能：Excel数据导入类(本类职责：只负责根据请求的Excel文件上传,及解析Excel行数据为String数组。具体业务逻辑由组合接口ExcelExecute实现。)
 *
 * @version 2017年1月4日下午2:13:40
 * @author baocheng.ren
 */
public class ExcelImport {
    
    /**
     * 导入操作具体执行方法接口，不同的业务需根据业务自己实现这个接口
     */
    private ExcelExecute excelExecute;
    
    @Autowired
    private CommonsMultipartResolver multipartResolver;
    
    /**
     * 功能：上传文件，并导入Excel方法
     *
     * @version 2017年1月4日下午2:13:56
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @param columnSize columnSize
     * @param diyParams diyParams
     * @throws IOException IOException
     */
    public void importExcel(HttpServletRequest request, HttpServletResponse response, int columnSize, 
            String[] diyParams)
            throws IOException {
        String[] names = upload(request, response);
        if (names == null) {
            return;
        }
        String fileName = names[0];
        
        // 解读excel并操作数据
        execExcel(fileName, columnSize, diyParams);
        
        // 删除临时上传文件
        File file = new File(fileName);
        file.delete();
        // 导入结果处理
        this.execResult(response, names[1]);
    }
    
    /**
     * 功能：文件上传方法
     *
     * @version 2017年1月4日下午2:14:39
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @return String[]
     * @throws IOException IOException
     */
    private String[] upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mfile = null;
        if (multipartResolver.isMultipart(multipartRequest)) { // 判断 request 是否有文件上传
            MultiValueMap<String, MultipartFile> multfiles = multipartRequest.getMultiFileMap();
            for (String srcfname : multfiles.keySet()) {
                mfile = multfiles.getFirst(srcfname);
            }
        }
        else {
            return null;
        }
        if (mfile == null) {
            return null;
        }
        String clientName = new String(mfile.getOriginalFilename().getBytes("ISO8859-1"), "UTF-8");
        // 文件上传表单域
        ServletContext sctx = request.getSession().getServletContext();
        String path = sctx.getRealPath("");
        // 获得客户端上传的文件名
        
        String suffix = null;
        if (clientName.endsWith(".xlsx")) {
            suffix = ".xlsx";
        }
        else if (clientName.endsWith(".xls")) {
            suffix = ".xls";
        }
        else {
            response.setContentType("text/html;charset=utf-8");
            response.getOutputStream().write("请上传Excel格式的文件!".getBytes("utf-8"));
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 建立系统里面的新文件名
        String filePath = path + "/" + "outsource" + format.format(new Date()) + suffix;
        mfile.transferTo(new File(filePath));
        System.out.println("上传文件完成!FilePath:" + path + "/" + clientName);
        System.out.println("上传文件完成!" + filePath);
        return new String[] { filePath, clientName };
    }
    
    /**
     * 功能：解析读取excel并操作数据
     *
     * @version 2017年1月4日下午2:15:14
     * @author baocheng.ren
     * @param filePath 路径
     * @param columnSize 列数
     * @param diyParams 自定义的传递参数
     * @throws IOException IOException
     */
    private void execExcel(String filePath, int columnSize, String[] diyParams) throws IOException {
        boolean isE2007 = false; // 判断是否是excel2007格式
        if (filePath.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = new FileInputStream(filePath); // 建立输入流
        Workbook wb = null;
        // 根据文件格式(2003或者2007)来初始化
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        }
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
        Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
        int mark = 0; // 行标识
        while (rows.hasNext()) {
            mark++;
            Row row = rows.next(); // 获得行数据
            if (mark == 1) { // 不写入第一行
                continue;
            }
            String[] array = this.coverRowToArray(row, columnSize);
            if (array == null) { // 为空行
                mark--;
                continue;
            }
            excelExecute.execute(array, diyParams);
        }
    }
    
    /**
     * 功能：处理excel解析结果
     *
     * @version 2017年1月4日下午2:16:04
     * @author baocheng.ren
     * @param response response
     * @param message message
     * @throws IOException IOException
     */
    private void execResult(HttpServletResponse response, String message) throws IOException {
        // 跳转到成功提示页面
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write("导入完成!".getBytes("utf-8"));
    }
    
    /**
     * 功能：从excel的行Row转换为String[].如果整行为空返回null
     *
     * @version 2017年1月4日下午2:16:22
     * @author baocheng.ren
     * @param row 行
     * @param fieldSize fieldSize
     * @return String[]
     */
    private String[] coverRowToArray(Row row, int fieldSize) {
        if (row == null) {
            return null;
        }
        String[] array = new String[fieldSize];
        boolean nullMark = true;
        for (int i = 0; i < fieldSize; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            
            boolean isCellNull = false;
            switch (cell.getCellType()) { // 根据cell中的类型来输出数据
                case HSSFCell.CELL_TYPE_NUMERIC:String v = cell.getNumericCellValue() + "";
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
                        v = simpledateformat.format(date);
                    }
                    array[i] = v;
                    break;
            
                case HSSFCell.CELL_TYPE_STRING:
                    String value = cell.getStringCellValue();
                    if (value == null || "".equals(value)) {
                        isCellNull = true;
                        break;
                    }
                    else {
                        isCellNull = false;
                    }
                    array[i] = value;
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    boolean boolValue = cell.getBooleanCellValue();
                    array[i] = String.valueOf(boolValue);
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    String formulaValue = cell.getCellFormula();
                    array[i] = formulaValue;
                    break;
                default:
                    isCellNull = true;
                    break;
            }
            if (!isCellNull) {
                nullMark = false;
            }
        }
        
        if (nullMark) {
            return null;
        }
        return array;
    }
    
    public ExcelExecute getExcelExecute() {
        return excelExecute;
    }
    
    public void setExcelExecute(ExcelExecute excelExecute) {
        this.excelExecute = excelExecute;
    }
}
