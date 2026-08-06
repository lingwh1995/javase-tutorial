﻿package org.bluebridge.section_12_jdk12.unit_03_number;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * JDK 12 CompactNumberFormat（STANDARD 特性）
 *      使用 NumberFormat.getCompactNumberInstance() 创建紧凑数字格式
 *      支持 SHORT 和 LONG 两种格式风格
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class CompactNumberFormatTest {

    /**
     * 测试中文环境下的紧凑数字格式（SHORT 和 LONG）
     */
    @Test
    public void testChineseCompactNumber() {
        long[] numbers = {1000, 10000, 1000000, 1000000000, 123456789L};

        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.LONG);

        System.out.println("===== 中文紧凑数字格式 =====");
        for (long num : numbers) {
            System.out.println(num + " -> SHORT: " + shortFormat.format(num) + ", LONG: " + longFormat.format(num));
        }
    }

    /**
     * 测试英文环境下的紧凑数字格式（SHORT 和 LONG）
     */
    @Test
    public void testEnglishCompactNumber() {
        long[] numbers = {1000, 10000, 1000000, 1000000000, 123456789L};

        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        System.out.println("===== 英文紧凑数字格式 =====");
        for (long num : numbers) {
            System.out.println(num + " -> SHORT: " + shortFormat.format(num) + ", LONG: " + longFormat.format(num));
        }
    }

    /**
     * 测试不同语言环境（德语、日语）的紧凑数字格式
     */
    @Test
    public void testMultiLocaleCompactNumber() {
        long number = 10000000;

        NumberFormat cnShort = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        NumberFormat usShort = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat deShort = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        NumberFormat jpShort = NumberFormat.getCompactNumberInstance(Locale.JAPAN, NumberFormat.Style.SHORT);

        System.out.println("===== 不同语言环境对比 =====");
        System.out.println("数字: " + number);
        System.out.println("中文: " + cnShort.format(number));
        System.out.println("英文: " + usShort.format(number));
        System.out.println("德文: " + deShort.format(number));
        System.out.println("日文: " + jpShort.format(number));
    }

    /**
     * 测试浮点数紧凑数字格式
     */
    @Test
    public void testDecimalCompactNumber() {
        double[] numbers = {999.5, 1500.7, 12345.67, 999999.99};

        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        System.out.println("===== 浮点数紧凑数字格式 =====");
        for (double num : numbers) {
            System.out.println(num + " -> SHORT: " + shortFormat.format(num) + ", LONG: " + longFormat.format(num));
        }
    }

    /**
     * 测试极小数字的紧凑格式
     */
    @Test
    public void testSmallNumberCompact() {
        int[] numbers = {0, 1, 10, 100, 500, 999};

        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

        System.out.println("===== 极小数字紧凑格式 =====");
        for (int num : numbers) {
            System.out.println(num + " -> " + shortFormat.format(num));
        }
    }
}