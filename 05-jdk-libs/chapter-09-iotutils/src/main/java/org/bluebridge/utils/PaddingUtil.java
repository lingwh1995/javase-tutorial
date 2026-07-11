package org.bluebridge.utils;

import java.util.Arrays;

/**
 * @author lingwh
 * @desc 补齐工具类
 * @date 2026/7/9 00:00
 */
public class PaddingUtil {

    /**
     * 例如需要N字节补齐，报文长度少于N个字节，需要补满N个字节，补(N-len)个(N-len)。 如果报文长度正好是N字节的整数倍，则需要补16个十进制16。
     *
     * @param data 原始字节数组
     * @param paddingLength 需要多少字节补齐
     * @return 补齐后的字节数组
     */
    public static byte[] padding(byte[] data, int paddingLength) {
        // 需要补齐的字节长度、数据值
        int paddingNumber = paddingLength - data.length % paddingLength;
        byte[] result = new byte[data.length + paddingNumber];
        System.arraycopy(data, 0, result, 0, data.length);
        for (int i = data.length; i < result.length; i++) {
            result[i] = (byte) paddingNumber;
        }
        return result;
    }

    public static byte[] padding(byte[] data) {
        byte[] result;
        int paddingNumber = data.length % 16;
        if (paddingNumber == 0) {
            int length = data.length + 16;
            result = new byte[length];
            System.arraycopy(data, 0, result, 0, data.length);
            for (int i = data.length; i < result.length; i++) {
                result[i] = (byte) 16;
            }
        } else {
            int value = 16 - paddingNumber;
            result = new byte[data.length + value];
            System.arraycopy(data, 0, result, 0, data.length);
            for (int i = data.length; i < result.length; i++) {
                result[i] = (byte) value;
            }
        }
        return result;
    }

    /**
     * 去除padding数据，取字节数组最后一个字节N,即padding了N个N,将这N个N截取掉，返回剩余的数组
     *
     * @param data 原始字节数组
     * @return 去掉padding后的数组
     */
    public static byte[] removePadding(byte[] data) {
        if (null == data || data.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        int paddingNumber = data[data.length - 1];
        if (data.length < paddingNumber) {
            throw new IllegalArgumentException("请确认该字节数组不符合数据补齐规范");
        }
        for (int i = 0; i < paddingNumber; i++) {
            if (paddingNumber != data[data.length - 1 - i]) {
                throw new IllegalArgumentException("请确认该字节数组不符合数据补齐规范");
            }
        }
        return Arrays.copyOfRange(data, 0, data.length - paddingNumber);
    }

    public static void main(String[] args) {
        byte[] array = new byte[] {1, 2, 3, 4, 5, 6};
        // 16字节补齐 补10个10
        byte[] padding16 = padding(array, 16);
        System.out.println(Arrays.toString(padding16));
        // 去除补齐数据
        byte[] originPadding16 = removePadding(padding16);
        System.out.println(Arrays.toString(originPadding16));

        // 8字节补齐 补2个2
        byte[] padding8 = padding(array, 8);
        System.out.println(Arrays.toString(padding8));
        // 去除补齐数据
        byte[] originPadding8 = removePadding(padding16);
        System.out.println(Arrays.toString(originPadding8));
    }
}
