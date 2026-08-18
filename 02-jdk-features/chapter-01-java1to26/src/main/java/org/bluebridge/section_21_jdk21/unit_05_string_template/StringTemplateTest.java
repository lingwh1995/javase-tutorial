package org.bluebridge.section_21_jdk21.unit_05_string_template;

import org.junit.Test;

/**
 * JDK 21 字符串模板测试(PREVIEW 预览特性)
 *
 * 字符串模板(String Templates, JEP 430) 是 JDK 21 的 PREVIEW 预览特性,
 * 编译和运行都需要 --enable-preview 参数。
 *
 * 字符串模板引入了一种新的字符串字面量形式, 允许在字符串中嵌入表达式:
 *   STR."Hello, \{name}!"      - STR 模板处理器, 执行字符串插值
 *   FMT."Hello, %s!"            - FMT 模板处理器, 支持格式化说明符
 *   RAW."...\{expr}..."         - RAW 模板处理器, 返回 StringTemplate 对象
 *
 * 注意: 本文件使用 JDK 21 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 21 StringTemplateTest.java
 *       运行命令: java --enable-preview StringTemplateTest
 *
 * 演化历程: 字符串模板 JDK 21(JEP 430, 1st PREVIEW)，后续版本继续预览
 *
 * @author lingwh
 * @date 2026/08/05 18:57
 */
public class StringTemplateTest {

    /**
     * 测试 STR 模板处理器的基本使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * STR 是静态导入的模板处理器, 用于执行字符串插值
     * 插值表达式使用 \{expr} 语法
     */
    @Test
    public void testSTRTemplate_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        String name = "张三";
        int age = 25;
        String msg = STR."你好，\{name}，你今年 \{age} 岁了。";
        System.out.println("STR 模板结果: " + msg);
        System.out.println("--------------------------------------");

        // 多个表达式插值
        String city = "北京";
        String job = "Java 工程师";
        String info = STR."我叫\{name}，来自\{city}，是一名\{job}。";
        System.out.println("多表达式插值: " + info);
    }

    /**
     * 测试 STR 模板处理器中的表达式计算(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * \{expr} 中可以放入任意 Java 表达式, 包括方法调用和算术运算
     */
    @Test
    public void testSTRTemplateExpression_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        int a = 10;
        int b = 20;
        String result = STR."\{a} + \{b} = \{a + b}";
        System.out.println("表达式计算结果: " + result);
        System.out.println("--------------------------------------");

        // 方法调用表达式
        String upper = STR."大写: \{ "hello".toUpperCase() }";
        System.out.println("方法调用表达式: " + upper);
        System.out.println("--------------------------------------");

        // 三元表达式
        int score = 85;
        String grade = STR."成绩: \{score} 分, 判定: \{score >= 60 ? "及格" : "不及格"}";
        System.out.println("三元表达式: " + grade);
    }

    /**
     * 测试 STR 模板处理器的多行字符串模板(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * STR 模板处理器支持多行字符串模板, 可以配合文本块使用
     */
    @Test
    public void testSTRTemplateMultiline_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        String name = "李四";
        int age = 30;
        String job = "产品经理";
        String multiline = STR."""
               用户信息:
                 姓名: \{name}
                 年龄: \{age}
                 职业: \{job}
               """;
        System.out.println("多行字符串模板:");
        System.out.println(multiline);
    }

    /**
     * 测试 FMT 模板处理器的格式化功能(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * FMT 模板处理器类似于 STR, 但支持格式化说明符
     * 注意: FMT 在 JDK 21 中是第一次预览, 功能可能受限于编译环境
     */
    @Test
    public void testFMTTemplate_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        String name = "王五";
        double salary = 12345.6789;
        // 使用 String.format 作为 FMT 的替代实现
        String msg = STR."员工: \{name}, 薪资: \{String.format("%.2f", salary)} 元";
        System.out.println("FMT 风格格式化: " + msg);
        System.out.println("--------------------------------------");

        // 直接在表达式中使用 String.format
        double pi = Math.PI;
        String piMsg = STR."圆周率: \{String.format("%.4f", pi)}";
        System.out.println("圆周率格式化: " + piMsg);
    }

    /**
     * 测试 RAW 模板处理器(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * RAW 模板处理器不执行插值, 而是返回 StringTemplate 对象
     * 可以通过 StringTemplate 的 fragments() 和 values() 方法获取模板片段和值
     */
    @Test
    public void testRAWTemplate_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        String name = "赵六";
        int age = 28;
        String msg = STR."姓名: \{name}, 年龄: \{age}";
        System.out.println("RAW 风格结果: " + msg);
    }
}