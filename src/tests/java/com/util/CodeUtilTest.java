package com.util;

import org.junit.Assert;
import org.junit.Test;

public class CodeUtilTest {

    @Test
    public void testEscape() {
        Assert.assertEquals("%80%u9e00Ё%00A",
                CodeUtil.escape("\u0080\u9e00\u0401\u0000A"));
    }

    @Test
    public void testUnescape() {
        Assert.assertEquals("fo\u0011ar", CodeUtil.unescape("fo%11ar"));
    }

    @Test
    public void testDbEncode() {
        Assert.assertNull(CodeUtil.dbEncode(null));
        Assert.assertEquals("%25E2%2599%25A7", CodeUtil.dbEncode("♧"));
    }

    @Test
    public void testDbDecode() {
        Assert.assertNull(CodeUtil.dbDecode(null));
        Assert.assertEquals("â\u0099¢", CodeUtil.dbDecode("%E2%99%A2"));
    }

    @Test
    public void testToText() {
        Assert.assertEquals("", CodeUtil.toText(null));
        Assert.assertEquals("foo", CodeUtil.toText("foo"));
    }

    @Test
    public void testToHtml() {
        Assert.assertEquals("", CodeUtil.toHtml(null));
        Assert.assertEquals("foo", CodeUtil.toHtml("foo"));
    }

    @Test
    public void testToUnicode() {
        Assert.assertEquals("1", CodeUtil.toUnicode("1", true));
        Assert.assertEquals(">\\ \\\\\\u0000\\r\\t\\n\\f\\!",
                CodeUtil.toUnicode("> \\\u0000\r\t\n\f!", true));
    }

    @Test
    public void testFromUnicode() {
        CodeUtil codeUtil = new CodeUtil();
        final char[] in1 = {'t', '\\', '\\', 'p', '\\',
                'u', '0', 'a', 'A', 'a'};
        final char[] convtBuf1 = {'\u0139', '\u02f9',
                '\u01e1', '\u001e', 'p', '@', 'U'};

        Assert.assertEquals("\\pપ",
                codeUtil.fromUnicode(in1, 1, 8, convtBuf1));

        final char[] in2 = {'\\', '0', '\\', 'r',
                '\\', 't', '\\', 'f', '\\', 'n'};
        final char[] convtBuf2 = {'\u0139', '\u02f9', '\u0120',
                '\u001e', 'u', 'H', 'T', 'u', 'B', '\u0000'};

        Assert.assertEquals("0\r\t\f\n",
                codeUtil.fromUnicode(in2, 1, 8, convtBuf2));
    }
}
