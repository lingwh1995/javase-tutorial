package org.bluebridge.section_07_jdk7.unit_07_literal;

import org.junit.Test;

/**
 * JDK 7 字面量改进测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class LiteralTest {

    /**
     * 测试二进制字面量
     */
    @Test
    public void testBinaryLiteral() {
        // JDK 7 引入二进制字面量，以 0b 或 0B 开头
        int binary1 = 0b1010;       // 二进制 1010 = 十进制 10
        int binary2 = 0b1111;       // 二进制 1111 = 十进制 15
        int binary3 = 0b10000000;   // 二进制 10000000 = 十进制 128
        int binary4 = 0b11111111;   // 二进制 11111111 = 十进制 255

        System.out.println("0b1010 = " + binary1);
        System.out.println("0b1111 = " + binary2);
        System.out.println("0b10000000 = " + binary3);
        System.out.println("0b11111111 = " + binary4);

        // 二进制字面量常用于位运算
        int flags = 0b00001111;
        int mask = 0b00000001;
        boolean isSet = (flags & mask) != 0;
        System.out.println("位运算: flags & mask = " + (flags & mask) + " (最低位是否为 1: " + isSet + ")");
    }

    /**
     * 测试数字字面量中使用下划线分隔
     */
    @Test
    public void testUnderscoreInNumericLiteral() {
        // JDK 7 允许在数字字面量中使用下划线提高可读性
        // 下划线可以放在数字之间，但不能放在开头、结尾或符号旁边

        // 整型字面量
        int million = 1_000_000;
        long creditCard = 1234_5678_9012_3456L;
        long phoneNumber = 138_1234_5678L;
        int hexWithUnderscore = 0xFF_EC_DE_5E;
        int binaryWithUnderscore = 0b1101_0011_1010_0101;

        System.out.println("一百万: " + million);
        System.out.println("信用卡号: " + creditCard);
        System.out.println("电话号码: " + phoneNumber);
        System.out.println("十六进制带下划线: " + hexWithUnderscore);
        System.out.println("二进制带下划线: " + binaryWithUnderscore);

        // 浮点型字面量
        float pi = 3.1415_9265F;
        double scientific = 1_234_567.123_456;
        double hexFloat = 0x1.FFFF_FFFF_FFFF_FFP0;

        System.out.println("圆周率: " + pi);
        System.out.println("科学计数法: " + scientific);
        System.out.println("十六进制浮点数: " + hexFloat);
    }

    /**
     * 测试对比传统写法
     */
    @Test
    public void testTraditionalLiteral() {
        // 传统写法（JDK 7 之前）
        int million = 1000000;
        int binary255 = 255;  // 只能用十进制或十六进制 0xFF

        System.out.println("传统写法: million = " + million);
        System.out.println("传统写法: binary255 = " + binary255);

        // JDK 7 写法
        int millionWithUnderscore = 1_000_000;
        int binaryLiteral = 0b11111111;

        System.out.println("JDK 7 写法: million = " + millionWithUnderscore);
        System.out.println("JDK 7 写法: binary255 = " + binaryLiteral);

        // 对比可读性
        // 传统写法：1000000000 很难一眼看出数值大小
        // 下划线写法：1_000_000_000 一目了然
        long traditional = 1000000000L;
        long readable = 1_000_000_000L;
        System.out.println("可读性对比: 传统=" + traditional + ", 下划线=" + readable);
    }
}