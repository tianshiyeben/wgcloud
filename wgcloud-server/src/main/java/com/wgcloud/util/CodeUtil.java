package com.wgcloud.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @version v2.3
 * @ClassName:CodeUtil.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 编码转换
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class CodeUtil {
    /**
     * 把SRC编码成uncode
     * 方便AJAX传输
     *
     * @param src
     * @return
     */
    public static String escape(String src) {
        if (src == null) return src;
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j)
                    || Character.isLowerCase(j)

                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * 把SRC解出正常编码
     *
     * @param src
     * @return
     */
    public static String unescape(String src) {
        if (src == null) return src;
        StringBuffer tmp = new StringBuffer();

        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch =
                            (char) Integer.parseInt(
                                    src.substring(pos + 2, pos + 6),
                                    16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch =
                            (char) Integer.parseInt(
                                    src.substring(pos + 1, pos + 3),
                                    16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 前台解码
     *
     * @param src
     * @return
     */
    public static String decodeURIComponent(String src) {
        if (src == null) return src;
        String ret = null;
        try {
            ret = URLDecoder.decode(src, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 双重编码
     *
     * @param data
     * @return
     */
    public static String dbEncode(String data) {
        if (data == null) return null;
        return CodeUtil.escape(CodeUtil.encodeURIComponent(data));
    }

    /**
     * 双重解码
     *
     * @param data
     * @return
     */
    public static String dbDecode(String data) {
        if (data == null) return null;
        return CodeUtil.decodeURIComponent(CodeUtil.unescape(data));
    }

    /**
     * 前台编码
     *
     * @param src
     * @return
     */
    public static String encodeURIComponent(String src) {
        if (src == null) return src;
        String ret = null;
        try {
            ret = URLEncoder.encode(src, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * HTML转为TEXT
     *
     * @param str
     * @return
     */
    public static String toText(String str) {
        if (str == null) {
            return "";
        } else {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("'", "''");
            str = str.replaceAll(" ", "&nbsp;");
            str = str.replaceAll("\n", "<br>");
            str = str.replaceAll("\"", "&#034;");
        }
        return str;
    }

    /**
     * TEXT转为HTML
     *
     * @param str
     * @return
     */
    public static String toHtml(String str) {
        if (str == null) {
            return "";
        } else {
            str = str.replaceAll("&lt;", "<");
            str = str.replaceAll("&gt;", ">");
            str = str.replaceAll("''", "'");
            str = str.replaceAll("&nbsp;", " ");
            str = str.replaceAll("<br>", "\n");
            str = str.replaceAll("&#034;", "\"");
        }
        return str;
    }

    private static final char[] hexDigit = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /**
     * 将字符串编码成 Unicode 。
     *
     * @param theString   待转换成Unicode编码的字符串。
     * @param escapeSpace 是否忽略空格。
     * @return 返回转换后Unicode编码的字符串。
     */
    public static String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
                case ' ':
                    if (x == 0 || escapeSpace)
                        outBuffer.append('\\');
                    outBuffer.append(' ');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\');
                    outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    /**
     * 从 Unicode 码转换成编码前的特殊字符串。
     *
     * @param in       Unicode编码的字符数组。
     * @param off      转换的起始偏移量。
     * @param len      转换的字符长度。
     * @param convtBuf 转换的缓存字符数组。
     * @return 完成转换，返回编码前的特殊字符串。
     */
    public String fromUnicode(char[] in, int off, int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }


}
