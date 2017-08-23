/*
 * Copyright (C) 2015 ShenZhen HeShiDai Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.heshidai.com.
 */
package com.heshidai.gold.console.common.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.heshidai.gold.console.common.constant.CommonConstant;

/**
 * 生成随机验证码
 * 
 * @version 2016年3月14日下午1:30:19
 * @author du.dxlove
 */
@SuppressWarnings("serial")
public class ValidateCodeServlet extends HttpServlet {
    /**
     * 验证码键值
     */
    public static final String VALIDATE_CODE = "validateCode";
    
    private static final int NUMBER_255 = 255;
    private static final int NUMBER_250 = 250;
    private static final int NUMBER_220 = 220;
    private static final int NUMBER_150 = 150;
    private static final int NUMBER_100 = 100;
    private static final int NUMBER_70 = 70;
    private static final int NUMBER_50 = 50;
    private static final int NUMBER_40 = 40;
    private static final int NUMBER_26 = 26;
    private static final int NUMBER_19 = 19;
    private static final int NUMBER_15 = 15;
    private static final int NUMBER_8 = 8;
    private static final int NUMBER_5 = 5;
    private static final int NUMBER_4 = 4;
    
    private int w = NUMBER_70;
    private int h = NUMBER_26;
    
    public ValidateCodeServlet() {
        super();
    }
    
    /**
     * 销毁
     */
    public void destroy() {
        super.destroy();
    }
    
    /**
     * 功能：验证
     *
     * @version 2017年1月4日下午3:48:20
     * @author baocheng.ren
     * @param request request
     * @param validateCode validateCode
     * @return boolean
     */
    public static boolean validate(HttpServletRequest request, String validateCode) {
        String code = (String) request.getSession().getAttribute(VALIDATE_CODE);
        return validateCode.toUpperCase().equals(code);
    }
    
    /**
     * 功能：get
     *
     * @version 2017年1月4日下午3:48:20
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @exception ServletException ServletException
     * @exception IOException IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validateCode = request.getParameter(VALIDATE_CODE); // AJAX验证，成功返回true
        if (StringUtils.isNotBlank(validateCode)) {
            response.getOutputStream().print(validate(request, validateCode) ? "true" : "false");
        }
        else {
            this.doPost(request, response);
        }
    }
    
    /**
     * 功能：doPost
     *
     * @version 2017年1月4日下午3:48:20
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @exception ServletException ServletException
     * @exception IOException IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createImage(request, response);
    }
    
    /**
     * 功能：createImage
     *
     * @version 2017年1月4日下午3:48:20
     * @author baocheng.ren
     * @param request request
     * @param response response
     * @exception IOException IOException
     */
    private void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
        /*
         * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
         */
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        if (StringUtils.isNumeric(width) && StringUtils.isNumeric(height)) {
            w = NumberUtils.toInt(width);
            h = NumberUtils.toInt(height);
        }
        
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        /*
         * 生成背景
         */
        createBackground(g);
        
        /*
         * 生成字符
         */
        String s = createCharacter(g);
        
        // 获取用户名
        Object object = request.getParameter(CommonConstant.USER_NAME);
        String username = object == null ? "" : (String) object;
        
        request.getSession().setAttribute(VALIDATE_CODE + username, s);
        
        g.dispose();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.close();
        
    }
    
    /**
     * 功能：getRandColor
     *
     * @version 2017年1月4日下午3:50:44
     * @author baocheng.ren
     * @param fc fc
     * @param bc bc
     * @return Color
     */
    private Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > NUMBER_255) {
            f = NUMBER_255;
        }
        if (b > NUMBER_255) {
            b = NUMBER_255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }
    
    /**
     * 功能：createBackground
     *
     * @version 2017年1月4日下午3:50:57
     * @author baocheng.ren
     * @param g g
     */
    private void createBackground(Graphics g) {
        // 填充背景
        g.setColor(getRandColor(NUMBER_220, NUMBER_250));
        g.fillRect(0, 0, w, h);
        // 加入干扰线条
        for (int i = 0; i < NUMBER_8; i++) {
            g.setColor(getRandColor(NUMBER_40, NUMBER_150));
            Random random = new Random();
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int x1 = random.nextInt(w);
            int y1 = random.nextInt(h);
            g.drawLine(x, y, x1, y1);
        }
    }
    
    /**
     * 功能：createCharacter
     *
     * @version 2017年1月4日下午3:51:12
     * @author baocheng.ren
     * @param g g
     * @return String
     */
    private String createCharacter(Graphics g) {
        char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
                           'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
        String[] fontTypes = { "Arial", "Arial Black", "AvantGarde Bk BT", "Calibri" };
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < NUMBER_4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]); // random.nextInt(10));
            g.setColor(new Color(NUMBER_50 + random.nextInt(NUMBER_100), NUMBER_50 + random.nextInt(NUMBER_100),
                    NUMBER_50 + random.nextInt(NUMBER_100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, NUMBER_26));
            g.drawString(r, NUMBER_15 * i + NUMBER_5, NUMBER_19 + random.nextInt(NUMBER_8));
            // g.drawString(r, i*w/4, h-5);
            s.append(r);
        }
        return s.toString();
    }
}
