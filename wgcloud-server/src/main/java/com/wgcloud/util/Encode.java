package com.wgcloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @version v2.3
 * @ClassName:Encode.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: Encode.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class Encode {

    private static final Logger logger = LoggerFactory.getLogger(Encode.class);

    /**
     * 以UTF-8编码，再以系统默认编码解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String utf8ToSystem(String str) {
        return encode(str, "UTF-8", System.getProperty("file.encoding"));
    }

    /**
     * 以系统默认编码编码，再以UTF-8解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String systemToUtf8(String str) {
        return encode(str, System.getProperty("file.encoding"), "UTF-8");
    }

    /**
     * 以GBK编码，再以系统默认编码解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String gbkToSystem(String str) {
        return encode(str, "GBK", System.getProperty("file.encoding"));
    }

    /**
     * 以系统默认编码编码，再以GBK解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String systemToGbk(String str) {
        return encode(str, System.getProperty("file.encoding"), "GBK");
    }

    /**
     * 以ISO_8859_1编码，再以系统默认编码解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String iso_8859_1ToSystem(String str) {
        return encode(str, "ISO_8859_1", System.getProperty("file.encoding"));
    }

    /**
     * 以系统默认编码编码，再以ISO_8859_1解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String systemToIso_8859_1(String str) {
        return encode(str, System.getProperty("file.encoding"), "ISO_8859_1");
    }

    /**
     * 以ISO_8859_1编码，再以GBK解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String iso_8859_1ToGbk(String str) {
        return encode(str, "ISO_8859_1", "GBK");
    }

    /**
     * 以GBK编码，再以ISO_8859_1解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String gbkToIso_8859_1(String str) {
        return encode(str, "GBK", "ISO_8859_1");
    }

    /**
     * 以UTF-8编码，再以GBK解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String utf8ToGbk(String str) {
        return encode(str, "UTF-8", "GBK");
    }

    /**
     * 以GBK编码，再以UTF-8解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String gbkToUtf8(String str) {
        return encode(str, "GBK", "UTF-8");
    }

    /**
     * 以UTF-8编码，再以ISO_8859_1解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String utf8ToIso_8859_1(String str) {
        return encode(str, "UTF-8", "ISO_8859_1");
    }

    /**
     * 以ISO_8859_1编码，再以UTF-8解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String iso_8859_1ToUtf8(String str) {
        return encode(str, "ISO_8859_1", "UTF-8");
    }

    /**
     * URL编码
     * 以系统默认码编码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String urlEncode(String str) {
        str = urlEncode(str, System.getProperty("file.encoding"));
        return str;
    }

    /**
     * URL编码
     * 以指定码编码
     *
     * @param str      编码前字串
     * @param encoding 指定编码
     * @return 编码后字串
     */
    public static String urlEncode(String str, String encoding) {
        try {
            str = URLEncoder.encode(str, encoding);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return str;
    }

    /**
     * URL解码
     * 以系统默认码解码
     *
     * @param str 编码前字串
     * @return 编码后字串
     */
    public static String urlDecode(String str) {
        str = urlDecode(str, System.getProperty("file.encoding"));
        return str;
    }

    /**
     * URL解码
     * 以指定码解码
     *
     * @param str      编码前字串
     * @param encoding 指定编码
     * @return 编码后字串
     */
    public static String urlDecode(String str, String encoding) {
        try {
            str = URLDecoder.decode(str, encoding);
        } catch (UnsupportedEncodingException ex) {
            logger.error("处理乱码异常" + ex.toString());
            return "";
        }
        return str;
    }

    /**
     * 对中文URL编码，用来在浏览器端访问Linux服务器上资源
     * 以指定码编码，只针对中文
     *
     * @param str      编码前字串
     * @param encoding 指定编码
     * @return 编码后字串
     */
    public static String urlEncodeForLinux(String str, String encoding) {
        str = Encode.gbkToSystem(str);
        str = Encode.urlEncode(str, encoding);
        str = str.replaceAll("\\+", "%20");

        return str;
    }

    /**
     * 编码转换,将字符串为指定编码
     *
     * @param str       编码前字串
     * @param encodeStr 原字符串编码
     * @param decodeStr 指定编码
     * @return 编码后字串
     */
    public static String encode(String str, String encodeStr, String decodeStr) {
        try {
            str = new String(str.getBytes(encodeStr), decodeStr);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
            return null;
        }

        return str;
    }

    /**
     * URL解码
     * 以指定码解码
     *
     * @param str      编码前字串
     * @param encoding 指定编码
     * @return 编码后字串
     */
    public static String luanmaStr(String str) {
        try {
            str = new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException ex) {
            logger.error("处理乱码异常" + ex.toString());
            return "";
        }
        return str;
    }

    public static void main(String[] args) {
        //测试urlDecode()方法
        //String s = "%E6%9C%AC%E5%9C%B0+%E6%9C%AC%E5%9C%B0";
//        String s = "本地+ 本地";
//        s = urlEncode(s, "UTF-8");

//        s = "%2Froot%2Fimage%2F+plmm";
        //s = urlDecode(s, "ISO_8859_1");
//        s = urlDecode(s, "UTF-8");
        String mytext = null;
        String mytext2 = null;
        try {
            mytext = URLEncoder.encode("中华", "utf-8");
            mytext2 = urlDecode(mytext, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(mytext);
        System.out.println(mytext2);
    }

}
