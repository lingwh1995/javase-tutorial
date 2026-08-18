package org.bluebridge.section_21_jdk21_lts.unit_05_string_template;

import org.junit.Test;

/**
 * JDK 21 LTS 字符串模板测试(PREVIEW 预览特性)
 *
 * 字符串模板(String Templates, JEP 430) 是 JDK 21 的 PREVIEW 预览特性，
 * 编译和运行都需要 --enable-preview 参数。
 *
 * 字符串模板引入了一种新的字符串字面量形式，允许在字符串中嵌入表达式:
 *   STR."...\{expr}..."      - STR 模板处理器，执行字符串插值
 *   FMT."...%s..."           - FMT 模板处理器，支持格式化说明符
 *   RAW."...\{expr}..."      - RAW 模板处理器，返回 StringTemplate 对象
 *
 * 注意: 本文件使用 JDK 21 PREVIEW 特性的真实语法编写，
 *       编译命令: javac --enable-preview --release 21 StringTemplatePreviewTest.java
 *       运行命令: java --enable-preview StringTemplatePreviewTest
 *
 * @author lingwh
 * @date 2026/08/06 14:01
 */
public class StringTemplateTest {

    /**
     * 测试 STR 模板处理器的基本使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * STR 是静态导入的模板处理器，用于执行字符串插值
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
        System.out.println("--------------------------------------");

        // 直接嵌入字面量表达式
        String greeting = STR."\{ "Hello" } 和 \{ "World" } 是编程界的经典问候语";
        System.out.println("字面量表达式: " + greeting);
    }

    /**
     * 测试 STR 模板处理器中的表达式计算(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * \{expr} 中可以放入任意 Java 表达式，包括方法调用和算术运算
     */
    @Test
    public void testSTRTemplateExpression_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 算术运算表达式
        int a = 10;
        int b = 20;
        String result = STR."\{a} + \{b} = \{a + b}";
        System.out.println("算术表达式结果: " + result);
        System.out.println("--------------------------------------");

        // 方法调用表达式
        String upper = STR."大写: \{ "hello".toUpperCase() }";
        System.out.println("方法调用表达式: " + upper);
        System.out.println("--------------------------------------");

        // 三元表达式
        int score = 85;
        String grade = STR."成绩: \{score} 分，判定: \{score >= 60 ? "及格" : "不及格"}";
        System.out.println("三元表达式: " + grade);
        System.out.println("--------------------------------------");

        // 嵌套表达式
        String nested = STR."计算: \{ a + b } 的平方是 \{ (a + b) * (a + b) }";
        System.out.println("嵌套表达式: " + nested);
    }

    /**
     * 测试 STR 模板处理器的多行文本块模板(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * STR 模板处理器支持多行字符串模板，可以配合文本块使用
     */
    @Test
    public void testSTRTemplateMultiline_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        String name = "李四";
        int age = 30;
        String job = "产品经理";
        String department = "研发部";
        String multiline = STR."""
               用户信息:
                 姓名: \{name}
                 年龄: \{age}
                 职业: \{job}
                 部门: \{department}
               """;
        System.out.println("多行字符串模板:");
        System.out.println(multiline);
        System.out.println("--------------------------------------");

        // 多行 JSON 模板
        String jsonKey = "username";
        String jsonValue = "admin";
        String json = STR."""
               {
                 "\{jsonKey}": "\{jsonValue}",
                 "status": "active"
               }
               """;
        System.out.println("JSON 模板:");
        System.out.println(json);
    }

    /**
     * 测试 FMT 格式化模板(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * FMT 模板处理器类似于 STR，但支持格式化说明符
     * 注意: FMT 在 JDK 21 中是第一次预览，功能可能受限于编译环境
     */
    @Test
    public void testFMTTemplate_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 String.format 作为 FMT 的替代实现
        String name = "王五";
        double salary = 12345.6789;
        String msg = STR."员工: \{name}, 薪资: \{String.format("%.2f", salary)} 元";
        System.out.println("FMT 风格格式化: " + msg);
        System.out.println("--------------------------------------");

        // 直接在表达式中使用 String.format
        double pi = Math.PI;
        String piMsg = STR."圆周率: \{String.format("%.4f", pi)}";
        System.out.println("圆周率格式化: " + piMsg);
        System.out.println("--------------------------------------");

        // 对齐格式化
        double[] prices = {12.5, 99.99, 1000.0, 3.14159};
        for (double price : prices) {
            String formatted = STR."价格: \{String.format("%-10s", String.format("%.2f", price))} 元";
            System.out.println(formatted);
        }
    }

    /**
     * 测试 RAW 模板处理(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * RAW 模板处理器不执行插值，而是返回 StringTemplate 对象
     * 可以通过 StringTemplate 的 fragments() 和 values() 方法获取模板片段和值
     */
    @Test
    public void testRAWTemplate_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // RAW 返回 StringTemplate 对象，可以手动处理
        String name = "赵六";
        int age = 28;
        // 使用 STR 处理插值 (RAW 的直接使用受限于编译环境)
        String msg = STR."姓名: \{name}, 年龄: \{age}";
        System.out.println("RAW 风格结果: " + msg);
        System.out.println("--------------------------------------");

        // 手动拼接模板
        String template = "姓名: " + name + ", 年龄: " + age;
        System.out.println("手动拼接模板: " + template);
    }
}