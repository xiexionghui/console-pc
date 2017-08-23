package com.heshidai.gold.console.common.util;

import java.security.MessageDigest;

/**
 * 功能：MD5
 *
 * @version 2017年1月4日下午2:26:55
 * @author baocheng.ren
 */
public class MD5 {
    
    private static final int HEXADECIMAL_255 = 0xFF;
    
    /**
     * 功能：md5转换
     *
     * @version 2017年1月4日下午2:27:06
     * @author baocheng.ren
     * @param str 字符串
     * @return String
     */
    public static String toDigest(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            // MessageDigest alga=MessageDigest.getInstance("SHA-1");
            alga.update(str.getBytes());
            byte[] digesta = alga.digest();
            return byte2hex(digesta);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 功能：转换
     *
     * @version 2017年1月4日下午2:27:52
     * @author baocheng.ren
     * @param b 字节
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & HEXADECIMAL_255));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + "";
            }
        }
        return hs;
    }
    
    /**
     * 功能：转换
     *
     * @version 2017年1月4日下午2:28:14
     * @author baocheng.ren
     * @param str 字符串
     * @return String
     */
    public static String cjdaoMD5(String str) {
        String mm = MD5.toDigest(str);
        return mm;
    }
    
}