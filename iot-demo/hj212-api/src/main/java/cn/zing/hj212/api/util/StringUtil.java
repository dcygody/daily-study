package cn.zing.hj212.api.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Objects;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-27 13:56
 */
public class StringUtil {

    public static String getSubValue(String str, String start, String end) {
        int index = str.indexOf(start) + start.length();
        str = str.substring(index, str.indexOf(end, index));
        return str;
    }

    public static String getLength(String str) {
        str = ("000" + str.length());
        str = str.substring(str.length() - 4, str.length());
        return str;
    }

    public static String bytesToHexString(byte src) {
        StringBuilder stringBuilder = new StringBuilder("");
        int v = src & 0xFF;
        String hv = Integer.toHexString(v);
        if (hv.length() < 2) {
            stringBuilder.append(0);
        }
        stringBuilder.append(hv);
        return stringBuilder.toString();
    }

    private static byte encheck(byte[] buffer, int index, int length) {
        byte b = 0;
        for (int i = 0; i < length; i++) {
            b ^= buffer[index + i];
        }
        return b;
    }

    /**
     * 将字符串转换为字节
     *
     * @param content
     * @return
     */
    public static byte[] encode(String content) {
        byte[] result = null;
        byte[] bytes = content.getBytes();
        byte value = encheck(bytes, 0, bytes.length);
        String s = bytesToHexString(value);
        byte[] bytes2 = (s.length() == 1 ? "0" + s : s).getBytes();
        byte[] bytes3 = "####".getBytes();
        result = new byte[bytes.length + bytes2.length + bytes3.length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        System.arraycopy(bytes2, 0, result, bytes.length, bytes2.length);
        System.arraycopy(bytes3, 0, result, bytes.length + bytes2.length, bytes3.length);
        return result;
    }

    public static boolean isNotBlank(String str) {
        if (null != str && !str.equalsIgnoreCase("null") && str.length() > 0) {
            return true;
        }
        return false;
    }

    public static final String ZEROS = "00000000000000000000000000000000";
    public static final String DIGIT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String HEX = "0123456789ABCDEF";
    public static final String CHARSET = "UTF-8";
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = " ";
    public static final char[] TRIM_CHAR = {' ', '\t', '\n', '\r'};

    public static boolean nullity(String param) {
        return (param == null) || (param.trim().length() == 0);
    }

    public static String check(String param, String src_default) {
        if ((param == null) || (param.trim().length() == 0))
            return src_default;
        return param;
    }

    public static String null2emptystr(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    public static String null2emptystr(Object obj, String def) {
        return (obj == null) ? def : obj.toString();
    }

    public static String trim(String src) {
        if (nullity(src)) {
            return "";
        }
        return trim(src, TRIM_CHAR);
    }

    public static String trim(String src, String chs) {
        return trim(src, chs.toCharArray());
    }

    public static String trim(String src, char[] chArray) {
        int st = 0;
        int len = src.length();
        while (containChar(chArray, src.charAt(st)) && st < len) {
            ++st;
        }
        while ((st < len) && (containChar(chArray, src.charAt(len - 1)))) {
            --len;
        }
        return ((st > 0) || (len < src.length())) ? src.substring(st, len) : src;
    }

    static boolean containChar(char[] chArray, char ch) {
        for (char c : chArray)
            if (c == ch)
                return true;
        return false;
    }

    /**
     * 获取固定长度的数字，前补0
     *
     * @param number
     * @param len
     * @return
     */
    public static String int2str(String number, int len) {
        boolean negative = number.charAt(0) == '-';
        if (negative)
            number = number.substring(1);
        StringBuilder buf = new StringBuilder(ZEROS);
        buf.append(number);
        return (negative) ? '-' + buf.substring(buf.length() - len + 1) : buf.substring(buf.length() - len);
    }

    public static String int2str(int number, int len) {
        return int2str(String.valueOf(number), len);
    }

    /**
     * 获取固定长度的字母，前补A
     *
     * @param alpha
     * @param len
     * @return
     */
    public static String fixAlpha(String alpha, int len) {
        StringBuilder buf = new StringBuilder("AAAAAAAAAAAAAAAA");
        buf.append(alpha);
        return buf.substring(buf.length() - len);
    }

    /**
     * 数字转26字母进制
     *
     * @param num
     * @param base
     * @return
     */
    public static String NumberToAlpha(long num, int base) {
        if (num < 26) {
            return "" + DIGIT.charAt((int) num);
        } else {
            String str = NumberToAlpha(num / base, base);
            return str + DIGIT.charAt((int) (num % base));
        }
    }

    /**
     * 26字母进制转数字
     *
     * @param alpha
     * @return
     */
    public static Long AlphaToNumber(String alpha) {
        double num = 0;
        for (int i = 0; i < alpha.length(); i++) {
            num += DIGIT.indexOf(alpha.charAt(i)) * Math.pow(26, alpha.length() - i - 1);
        }
        return (long) num;
    }

    /**
     * 指定长度的数字表示的最大数
     *
     * @param len
     * @return
     */
    public static long getMaxVal(long len) {
        if (len <= 0) return 0;
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sbf.append("9");
        }
        return Long.parseLong(sbf.toString());
    }

    /**
     * 指定长度的字母表示的最大数
     *
     * @param len
     * @return
     */
    public static long getMaxAlphaVal(long len) {
        if (len <= 0) return 0;
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sbf.append("Z");
        }
        return AlphaToNumber(sbf.toString());
    }

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String str2hex(String str) {
        return str2hex(str, CHARSET);
    }

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String str2hex(String str, String charset) {
        // 根据默认编码获取字节数组
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (byte aByte : bytes) {
            sb.append(HEX.charAt((aByte & 0xf0) >> 4));
            sb.append(HEX.charAt((aByte & 0x0f)));
        }
        return sb.toString();
    }

    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String hex2str(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((HEX.indexOf(bytes.charAt(i)) << 4 | HEX.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    public static boolean isBlank(String string) {
        return null == string || string.length() == 0;
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static String str2utf8(String str) {
        if (str == null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > '\377') {
                sb.append("\\u");
                int j = c >>> 8;
                String tmp = Integer.toHexString(j);
                if (tmp.length() == 1)
                    sb.append('0');
                sb.append(tmp);
                j = c & 255;
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1)
                    sb.append('0');
                sb.append(tmp);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String utf82str(String str) {
        if (str == null)
            return null;
        if (!str.contains("\\u"))
            return str;
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\\' && i + 1 < str.length() && str.charAt(i + 1) == 'u'
                    && i + 5 < str.length()) {
                sb.append(utf8Ch2Str(str.substring(i + 2, i + 6)));
                i += 5;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String utf8Ch2Str(String str) {
        try {
            int c1 = Integer.parseInt(str.substring(0, 2), 16);
            int c2 = Integer.parseInt(str.substring(2, 4), 16);
            return String.valueOf((char) (c1 << 8 | c2));
        } catch (Exception exception) {
            return str;
        }
    }

    public static String printStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String stackTrace(StackTraceElement[] stes, int depth) {
        StringBuilder buf = new StringBuilder();
        if ((stes == null) && (depth == 0))
            return buf.toString();
        for (int i = 0; (i < Objects.requireNonNull(stes).length) && (((depth < 0) || (i < depth))); ++i) {
            buf.append(stes[i]);
            buf.append('\n');
        }
        return buf.toString();
    }

    public static String subStrByBytes(String string, int length, String charset) {
        try {
            byte[] b = string.getBytes(charset);
            if (b.length < length) {
                return string;
            } else {
                byte[] temp = new byte[length];
                System.arraycopy(b, 0, temp, 0, length);
                return new String(temp, charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string.substring(0, length);
    }

    public static String base64(String str, String charset) {
        try {
            return new String(encodeBase64(str.getBytes(charset)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String base64(byte[] buf) {
        if (buf == null)
            return "";
        return new String(Base64.getEncoder().encode(buf));
    }

    public static byte[] encodeBase64(byte[] buf) {
        return Base64.getEncoder().encode(buf);
    }

    public static byte[] decodeBase64(String str) {
        return Base64.getDecoder().decode(str.getBytes());
    }

    public static byte[] decodeBase64(byte[] buf) {
        return Base64.getDecoder().decode(buf);
    }

    public static String byte2binary(byte b) {
        StringBuilder buf = new StringBuilder();
        for (int i = 7; i >= 0; --i)
            buf.append(((1 << i) & b) == 0 ? '0' : '1');
        return buf.toString();
    }

    public static String bytes2binary(byte[] bs, String delim) {
        return bytes2binary(bs, 0, bs.length, delim);
    }

    public static String bytes2binary(byte[] bytes, int offset, int len, String delim) {
        if (offset + len > bytes.length) {
            throw new RuntimeException(offset + " + " + len + " is great than bytes' length " + bytes.length + "!");
        }
        StringBuilder buf = new StringBuilder();
        for (int j = offset; j < offset + len; j++) {
            if (buf.length() > 0) {
                buf.append(delim);
            }
            byte b = bytes[j];
            for (int i = 7; i >= 0; --i) {
                buf.append(((1 << i) & b) == 0 ? '0' : '1');
            }
        }
        return buf.toString();
    }

    public static byte[] binary2bytes(String binary) {
        if (binary == null || binary.length() % 8 != 0) {
            throw new NumberFormatException("binary's length isn't 8n.");
        }
        int size = binary.length() / 8;
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = (byte) Integer.parseInt(binary.substring(8 * i, 8 * i + 8), 2);
        }
        return bytes;
    }

    /**
     * 将字节数组变为二进制字符串分组打印
     *
     */
    public static String bytes2str(byte[] bytes, int column) {
        if (bytes == null || bytes.length == 0) return EMPTY_STRING;
        StringBuilder buf = new StringBuilder();
        int rows = bytes.length / column;
        if (bytes.length % column != 0)
            rows += 1;
        for (int i = 0; i < rows; i++) {
            StringBuilder byteBuf = new StringBuilder();
            StringBuilder textBuf = new StringBuilder();
            int rowLen = Math.min(column, bytes.length - i * column);
            for (int j = 0; j < rowLen; j++) {
                byte b = bytes[i * column + j];
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1)
                    byteBuf.append('0');
                byteBuf.append(hex.toUpperCase()).append(" ");

                if (b < 127 && b >= 32) {
                    textBuf.append((char) b);
                } else {
                    textBuf.append('.');
                }
            }
            if (byteBuf.length() < column * 3) {
                int spacesLen = column * 3 - byteBuf.length();
                for (int k = 0; k < spacesLen; k++) {
                    byteBuf.append(' ');
                }
            }
            if (textBuf.length() < column) {
                int spacesLen = column - textBuf.length();
                for (int k = 0; k < spacesLen; k++) {
                    textBuf.append(' ');
                }
            }
            buf.append(byteBuf.toString()).append("   ").append(textBuf.toString());
            if (rows > 1) {
                buf.append(System.getProperty("line.separator"));
            }
        }
        return buf.toString();
    }

    public static String int2hex(int value) {
        StringBuilder hexStr = new StringBuilder(Integer.toHexString(value));
        while (hexStr.length() < 8) {
            hexStr.insert(0, "0");
        }
        return hexStr.toString();
    }

    public static String int2binary(int value) {
        StringBuilder binaryStr = new StringBuilder(Integer.toBinaryString(value));
        while (binaryStr.length() < 32) {
            binaryStr.insert(0, "0");
        }
        return binaryStr.toString();
    }
}


