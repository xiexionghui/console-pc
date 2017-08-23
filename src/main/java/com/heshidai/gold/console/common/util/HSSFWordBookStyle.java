package com.heshidai.gold.console.common.util;

/**
 * 
 * 导出表格样式
 * @version 2017年1月18日下午5:09:48
 * @author dx.love
 */
public class HSSFWordBookStyle {
    
    private static final int FONT_HEIGHT_IN_POINTS = 11;
    
    /**
     * 字体，默认为 宋体
     */
    private String fontName = "宋体";
    
    /**
     * 字体大小，默认为 11
     */
    private short fontHeightInPoints = FONT_HEIGHT_IN_POINTS;
    
    /**
     * 是否左右居中，1:左对齐， 2：左右居中， 3：右对齐,默认为2
     */
    private short alignment = 2;
    
    /**
     * 是否上下居中，默认为 1：上下居中
     */
    private short verticalAlignment = 1;
    
    public HSSFWordBookStyle() {
        
    }
    
    public String getFontName() {
        return fontName;
    }
    
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    
    public short getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public void setFontHeightInPoints(short fontHeightInPoints) {
        this.fontHeightInPoints = fontHeightInPoints;
    }

    public short getAlignment() {
        return alignment;
    }
    
    public void setAlignment(short alignment) {
        this.alignment = alignment;
    }
    
    public short getVerticalAlignment() {
        return verticalAlignment;
    }
    
    public void setVerticalAlignment(short verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }
    
}
