package org.bluebridge.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.HexUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 常用协议解析工具类 依赖 hutools部分工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ParseUtil {

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        return HexUtil.encodeHexStr(bytes, false);
    }

    public static String bytesToHexString(byte[] bytes, int startIndex, int length) {
        if (startIndex > bytes.length - 1) {
            throw new IllegalArgumentException("起始下标超出数组长度");
        }
        if (startIndex + length > bytes.length) {
            throw new IllegalArgumentException("读取长度超出数组长度");
        }
        byte[] hexBytes = Arrays.copyOfRange(bytes, startIndex, startIndex + length);
        return HexUtil.encodeHexStr(hexBytes, false);
    }

    public static String bytesToHexString(byte[] bytes, int startIndex, int length, ByteOrder byteOrder) {
        if (startIndex > bytes.length - 1) {
            throw new IllegalArgumentException("起始下标超出数组长度");
        }
        if (startIndex + length > bytes.length) {
            throw new IllegalArgumentException("读取长度超出数组长度");
        }
        byte[] hexBytes = Arrays.copyOfRange(bytes, startIndex, startIndex + length);
        if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
            ArrayUtil.reverse(hexBytes);
        }
        return HexUtil.encodeHexStr(hexBytes, false);
    }

    public static String bytesToHexString(byte[] bytes, int startIndex) {
        return bytesToHexString(bytes, startIndex, bytes.length - startIndex);
    }

    /**
     * 字节数组转为字符串
     *
     * @param source ASCII字节数组
     * @param start 数组起始位置
     * @param len 读取长度
     * @return 字符串
     */
    public static String bytesToString(byte[] source, int start, int len) {
        return new String(source, start, len, StandardCharsets.UTF_8);
    }

    /**
     * 字节数组转为字符串
     *
     * @param source ASCII字节数组
     * @return 字符串
     */
    public static String bytesToString(byte[] source) {
        return new String(source, StandardCharsets.UTF_8);
    }

    /**
     * 获取单个bit值
     *
     * @param data 数值
     * @param pos 第几个bit位
     * @return 该bit位的值
     */
    public static int getBitNumber(int data, int pos) {
        return (data >> pos) & 0x1;
    }

    /**
     * 将字节数组转换为数值
     *
     * @param data 字节数组
     * @param byteOrder 大小端模式
     * @param start 数组起始下标
     * @param length 长度
     * @param scale 小数位数
     * @param unsigned 是否为无符号数
     * @return 数值
     */
    public static BigDecimal bytesToNumber(byte[] data, ByteOrder byteOrder, int start, int length, int scale, boolean unsigned) {
        byte[] value = Arrays.copyOfRange(data, start, start + length);
        if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
            ArrayUtil.reverse(value);
        }
        BigInteger bigInteger;
        if (unsigned) {
            bigInteger = new BigInteger(1, value);
        } else {
            bigInteger = new BigInteger(value);
        }
        return new BigDecimal(bigInteger, scale);
    }

    public static BigDecimal bytesToNumber(byte[] data, int start, int length, int scale, boolean unsigned) {
        return bytesToNumber(data, ByteOrder.BIG_ENDIAN, start, length, scale, unsigned);
    }

    public static byte[] numberToHexBytes(BigInteger value, ByteOrder byteOrder, int length) {
        byte[] result = new byte[length];

        // BigInteger.toByteArray()输出为大端模式
        byte[] bytes = value.toByteArray();

        // 前边可能有补0 截取掉
        int numberStart = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] != 0) {
                numberStart = i;
                break;
            }
        }
        if (numberStart > 0) {
            bytes = ArrayUtil.sub(bytes, numberStart, bytes.length);
        }

        if (ByteOrder.BIG_ENDIAN.equals(byteOrder)) {
            int start = Math.max(0, length - bytes.length);
            System.arraycopy(bytes, 0, result, start, Math.min(bytes.length, length));
            // 负数填充FF
            if (value.signum() == -1) {
                for (int i = 0; i < start; i++) {
                    result[i] = -1;
                }
            }
        } else {
            ArrayUtil.reverse(bytes);
            System.arraycopy(bytes, 0, result, 0, Math.min(bytes.length, length));
            // 负数填充FF
            if (value.signum() == -1) {
                for (int i = bytes.length; i < length; i++) {
                    result[i] = -1;
                }
            }
        }

        return result;
    }

    public static byte[] numberToHexBytes(String value, ByteOrder byteOrder, int scale, int length) {
        BigDecimal bigDecimal = new BigDecimal(value).movePointRight(scale);
        BigInteger integer = bigDecimal.toBigInteger();
        return numberToHexBytes(integer, byteOrder, length);
    }

    public static byte[] numberToHexBytes(BigDecimal bigDecimal, ByteOrder byteOrder, int movePoint, int length) {
        BigInteger integer = bigDecimal.movePointRight(movePoint).toBigInteger();
        return numberToHexBytes(integer, byteOrder, length);
    }

    public static byte[] numberToHexBytes(int value, ByteOrder byteOrder, int movePoint, int length) {
        BigInteger integer = BigDecimal.valueOf(value).movePointRight(movePoint).toBigInteger();
        return numberToHexBytes(integer, byteOrder, length);
    }

    public static byte[] numberToHexBytes(long value, ByteOrder byteOrder, int movePoint, int length) {
        BigInteger integer = BigDecimal.valueOf(value).movePointRight(movePoint).toBigInteger();
        return numberToHexBytes(integer, byteOrder, length);
    }

    /**
     * 字节数组转浮点数
     *
     * @param bytes 字节数组
     * @param byteOrder 大小端模式
     * @param startIndex 数组起始下标
     * @return 浮点数
     */
    public static float bytesToFloat(byte[] bytes, ByteOrder byteOrder, int startIndex) {
        if (null == bytes || bytes.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        if (startIndex + 4 > bytes.length) {
            throw new IllegalArgumentException("字节数组长度不足，需要4字节数组长度");
        }
        byte[] floatBytes = Arrays.copyOfRange(bytes, startIndex, startIndex + 4);
        return ByteUtil.bytesToFloat(floatBytes, byteOrder);
    }

    /**
     * 字节数组转浮点数
     *
     * @param bytes 字节数组
     * @param byteOrder 大小端模式
     * @return 浮点数
     */
    public static float bytesToFloat(byte[] bytes, ByteOrder byteOrder) {
        if (null == bytes || bytes.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        if (bytes.length != 4) {
            throw new IllegalArgumentException("字节数组长度需要为4字节");
        }
        return ByteUtil.bytesToFloat(bytes, byteOrder);
    }

    /**
     * 浮点数转字节数组
     *
     * @param floatValue 浮点数
     * @param byteOrder 大小端模式
     * @return 字节数组
     */
    public static byte[] floatToBytes(float floatValue, ByteOrder byteOrder) {
        return ByteUtil.floatToBytes(floatValue, byteOrder);
    }

    /**
     * 字节数组转双精度浮点数
     *
     * @param bytes 字节数组
     * @param byteOrder 大小端模式
     * @param startIndex 起始下标 从数组中该下标开始取8字节转换为double
     * @return 双精度浮点数
     */
    public static double bytesToDouble(byte[] bytes, ByteOrder byteOrder, int startIndex) {
        if (null == bytes || bytes.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        if (startIndex + 8 > bytes.length) {
            throw new IllegalArgumentException("字节数组长度不足，需要8字节数组长度");
        }
        byte[] doubleBytes = Arrays.copyOfRange(bytes, startIndex, startIndex + 8);
        return ByteUtil.bytesToDouble(doubleBytes, byteOrder);
    }

    /**
     * 字节数组转双精度浮点数
     *
     * @param bytes 字节数组
     * @param byteOrder 大小端模式
     * @return 双精度浮点数
     */
    public static double bytesToDouble(byte[] bytes, ByteOrder byteOrder) {
        if (null == bytes || bytes.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        if (bytes.length != 8) {
            throw new IllegalArgumentException("字节数组长度需要为8字节");
        }
        return ByteUtil.bytesToDouble(bytes, byteOrder);
    }

    /**
     * 双精度浮点数转字节数组
     *
     * @param doubleValue 双精度浮点数
     * @param byteOrder 大小端模式
     * @return 字节数组
     */
    public static byte[] doubleToBytes(double doubleValue, ByteOrder byteOrder) {
        return ByteUtil.doubleToBytes(doubleValue, byteOrder);
    }

    /**
     * 十六进制字符串转换为字节数据
     *
     * @param hexStr 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexStringToByte(String hexStr) {
        return HexUtil.decodeHex(hexStr);
    }

    public static String getDateFromCompressInt(int number) {
        long year = number / 372;
        long month = (number - year * 372) / 31 + 1;
        long dat = (number - year * 372 - (month - 1) * 31) + 1;
        year = year + 2000;
        return String.format("%02d-%02d-%02d", year, month, dat);
    }

    public static String getTimeFromCompressInt(int time) {
        if (time < 0) {
            time = -time;
        }
        int hour = time / 1800;

        if (hour < 0 || hour > 23) {
            hour = 0;
        }
        int minute = (time % 1800) / 30;
        if (minute < 0) {
            minute = 0;
        }
        int second = ((time % 1800) % 30) * 2;
        if (second < 0) {
            second = 0;
        }
        return String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
    }

    public static BigDecimal bytesToNumber(byte[] data, int start, int length, boolean unsigned, int scale) {
        byte[] value = Arrays.copyOfRange(data, start, start + length);
        BigInteger bigInteger;
        if (unsigned) {
            bigInteger = new BigInteger(1, value);
        } else {
            bigInteger = new BigInteger(value);
        }
        return new BigDecimal(bigInteger, scale);
    }

    public static long bytesToNumber(byte[] bytes, int startIndex, int length, boolean unsigned) {
        byte[] calcBytes = Arrays.copyOfRange(bytes, startIndex, startIndex + length);
        String hex = ParseUtil.byteToHexString(calcBytes);
        if (unsigned) {
            return Long.parseUnsignedLong(hex, 16);
        } else {
            return parseLong(calcBytes);
        }
    }

    private static long parseLong(byte[] bytes) {
        long a = bytes[0];
        for (int i = 1; i < bytes.length; i++) {
            a = (a << 8) | (bytes[i] & 0xFF);
        }
        return a;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String byteToHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
//        byte[] frameLengthBytes = ParseUtil.numberToHexBytes(2, ByteOrder.BIG_ENDIAN, 0, 2);
//        System.out.println(Arrays.toString(frameLengthBytes));
        byte[] bytes = new byte[2];
        bytes[0] = (byte) 0x68;
        String s = HexUtil.encodeHexStr(bytes);
        System.out.println(s);
        // byte[] bytes = doubleToBytes(2.0, ByteOrder.BIG_ENDIAN);
        // System.out.println(HexUtil.encodeHexStr(bytes));

    }

    public static String hexStringToBinaryString(String hexString) {
        String binary = new BigInteger(hexString, 16).toString(2);
        int completionZeroLength = hexString.length() / 2 * 8 - binary.length();
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, completionZeroLength).forEach(i -> {
            builder.append(0);
        });
        return builder.append(binary).toString();
    }

    /**
     * 将BCD码转成String
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bcdToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append((byte) ((aByte & 0xf0) >>> 4));
            sb.append((byte) (aByte & 0x0f));
        }
        return sb.toString();
    }
}
