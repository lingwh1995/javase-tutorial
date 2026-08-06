﻿package org.bluebridge.section_13_jdk13.unit_03_string;

import org.junit.Test;

/**
 * JDK 13 String.formatted() 方法测试
 *
 * 1. String.format(String format, Object... args): 静态方法, 根据格式字符串与参数生成格式化字符串
 * 2. String.formatted(Object... args): 实例方法, 等价于 String.format(this, args),
 *    直接在待格式化的字符串上调用, 更适合链式调用, 可读性更好
 * 3. 说明: JDK 13 中 formatted() 是文本块(PREVIEW 特性, JEP 355)的一部分,
 *    需要 javac --enable-preview 编译, java --enable-preview 运行;
 *    JDK 15(JEP 378)文本块转正为标准特性时, formatted() 也随之成为标准 API
 *
 * @author lingwh
 * @date 2026/08/05 18:29
 */
public class StringFormattedTest {

    /**
     * 测试 String.format() 静态方法: 使用 %s、%d、%.2f 等占位符格式化字符串
     */
    @Test
    public void testStringFormat() {
        // %s: 字符串占位符, %d: 整数占位符, %.2f: 保留两位小数的浮点数占位符
        String message = String.format("姓名: %s, 年龄: %d, 薪资: %.2f", "张三", 25, 8500.5);
        System.out.println("String.format() 格式化结果: " + message);
        System.out.println("--------------------------------------");
        // %x: 十六进制整数占位符, %%: 转义为字面量百分号
        String hex = String.format("数字 %d 的十六进制表示: %x", 255, 255);
        System.out.println(hex);
        String percent = String.format("及格率: %.1f%%", 87.5);
        System.out.println(percent);
    }

    /**
     * 测试 String.formatted() 方法(PREVIEW): formatted() 是文本块特性的一部分
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testFormatted_Preview() {
        // 真实 formatted() 调用: 实例方法, 等价于 String.format(this, args)
        String formattedResult = "hello, %s! 你今年 %d 岁.".formatted("张三", 25);
        System.out.println("formatted() 结果: " + formattedResult);
        System.out.println("--------------------------------------");
        // formatted() 是实例方法, 可以与其他字符串方法链式调用
        String upperCaseResult = "select * from user where name = '%s'".formatted("张三").toUpperCase();
        System.out.println("formatted() 链式调用结果: " + upperCaseResult);
        System.out.println("--------------------------------------");
        // 与文本块结合使用: 在文本块上调用 formatted() 填充占位符
        String template = """
            <html>
                <body>
                    <p>Hello, %s!</p>
                </body>
            </html>
            """.formatted("World");
        System.out.println("文本块 + formatted() 结果: ");
        System.out.println(template);
    }
}
