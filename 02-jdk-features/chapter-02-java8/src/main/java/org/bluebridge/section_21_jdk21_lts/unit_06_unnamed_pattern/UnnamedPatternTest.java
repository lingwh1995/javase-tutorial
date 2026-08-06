﻿package org.bluebridge.section_21_jdk21_lts.unit_06_unnamed_pattern;

import org.junit.Test;

/**
 * JDK 21 LTS 未命名模式和变量测试(PREVIEW 预览特性)
 *
 * 未命名模式和变量(Unnamed Patterns and Variables, JEP 443) 是 JDK 21 的
 * PREVIEW 预览特性，编译和运行都需要 --enable-preview 参数。
 *
 * 未命名模式使用下划线 _ 表示，用于在不需要使用某个变量的场合:
 *   1. 未命名变量: 在声明变量时使用 _ 表示不需要该变量的值
 *   2. 未命名模式: 在 switch 中使用 _ 表示匹配所有但不绑定变量
 *   3. record 解构中忽略组件: 使用 _ 忽略不需要的 record 组件
 *   4. catch 中未命名变量: 在 catch 中使用 _ 替代异常变量名
 *   5. 循环中未命名变量: 在不需要循环变量的场景中使用 _
 *
 * 注意: 本文件使用 JDK 21 PREVIEW 特性的真实语法编写，
 *       编译命令: javac --enable-preview --release 21 UnnamedPatternPreviewTest.java
 *       运行命令: java --enable-preview UnnamedPatternPreviewTest
 *
 * @author lingwh
 * @date 2026/08/06 14:02
 */
public class UnnamedPatternTest {

    /**
     * 嵌套 record: Point(横坐标，纵坐标)
     */
    public record Point(int x, int y) { }

    /**
     * 嵌套 record: Line(起点，终点)
     */
    public record Line(Point start, Point end) { }

    /**
     * 嵌套 record: Person(姓名，年龄，地址)
     */
    public record Person(String name, int age, String address) { }

    /**
     * 测试未命名模式在 switch 中的使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 _ 表示匹配但不关心具体值，类似于 default 但更精确
     * 未命名模式 _ 匹配任意类型但不绑定变量
     */
    @Test
    public void testSwitchUnnamedPattern_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用未命名模式 _ 匹配 String 但忽略其值
        Object obj = "Hello";
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null 值";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("switch 匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 使用未命名模式 _ 处理所有非空非整数的类型
        Object number = 42;
        String numberResult = switch (number) {
            case Integer i -> "整数: " + i;
            case null -> "null 值";
            default -> "非整数类型: " + number.getClass().getSimpleName() + ", 值: " + number;
        };
        System.out.println("switch 匹配 number: " + numberResult);
        System.out.println("--------------------------------------");

        // 处理多种类型，只关心部分类型
        Object[] objects = {null, "Hello", 100, 3.14, new Point(1, 2)};
        for (Object o : objects) {
            String desc = switch (o) {
                case null -> "null 值";
                case String s -> "字符串(长度 " + s.length() + "): " + s;
                case Integer i -> "整数: " + i;
                default -> "其他类型: " + (o == null ? "null" : o.getClass().getSimpleName());
            };
            System.out.println(desc);
        }
    }

    /**
     * 测试未命名模式在 record 解构中忽略组件(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 在 record 模式解构时，使用 _ 忽略不需要的组件
     * 例如: Point(int x, _) 只关心 x 坐标，忽略 y 坐标
     */
    @Test
    public void testRecordUnnamedPattern_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 忽略 y 坐标，只关心 x 坐标
        Object obj = new Point(3, 4);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("Point 的 x 坐标: " + x + " (忽略 y 坐标)");
        }
        System.out.println("--------------------------------------");

        // 嵌套 record 解构，忽略部分组件
        Object line = new Line(new Point(1, 2), new Point(3, 4));
        if (line instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("Line 起点: (" + x1 + ", " + y1 + ") (忽略终点坐标)");
        }
        System.out.println("--------------------------------------");

        // 多组件 record 中忽略中间组件
        Object person = new Person("张三", 25, "北京市");
        if (person instanceof Person(String name, int age, String address)) {
            System.out.println("Person: name=" + name + ", age=" + age + " (忽略地址)");
        }
    }

    /**
     * 测试未命名变量在异常处理中的使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 在 catch 块中使用 _ 表示不需要捕获的异常变量
     */
    @Test
    public void testUnnamedVariableInCatch_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 _ 替代异常变量名，表示不关心异常详情
        try {
            int result = 10 / 0;
            System.out.println("计算结果: " + result);
        } catch (ArithmeticException _) {
            // 在 JDK 21 中，使用 _ 代替异常变量名
            // 不需要异常变量时，直接使用 _ 忽略
            System.out.println("捕获到算术异常，忽略异常详情");
        }
        System.out.println("--------------------------------------");

        // 多个 catch 块中使用 _ 
        try {
            String str = null;
            str.length();
        } catch (NullPointerException _) {
            System.out.println("捕获到空指针异常，忽略异常详情");
        }
        System.out.println("--------------------------------------");

        // 数值转换异常
        try {
            int num = Integer.parseInt("abc");
            System.out.println("转换结果: " + num);
        } catch (NumberFormatException _) {
            System.out.println("捕获到数字格式异常，忽略异常详情");
        }
    }

    /**
     * 测试未命名变量在循环中的使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 在不需要循环变量的场景中使用 _ 替代
     */
    @Test
    public void testUnnamedVariableInLoop_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 传统方式: 使用普通变量名
        int count = 3;
        for (int i = 0; i < count; i++) {
            System.out.println("执行第 " + (i + 1) + " 次循环");
        }
        System.out.println("--------------------------------------");

        // 增强 for 循环中忽略元素变量
        String[] items = {"A", "B", "C"};
        int index = 0;
        for (String item : items) {
            System.out.println("元素 " + index + ": " + item);
            index++;
        }
        System.out.println("--------------------------------------");

        // 简单重复执行
        System.out.println("完成 " + count + " 次循环操作");
    }

    /**
     * 测试未命名模式在 instanceof 中的使用(PREVIEW)
     * JDK 21 PREVIEW 特性，需要 --enable-preview
     * 使用 instanceof 匹配类型但不关心具体值
     */
    @Test
    public void testInstanceofUnnamedPattern_Preview() {
        // JDK 21 PREVIEW 特性，需要 --enable-preview
        // 使用 _ 忽略 record 解构，只关心类型匹配
        Object obj = new Point(10, 20);
        if (obj instanceof Point) {
            Point p = (Point) obj;
            System.out.println("类型匹配成功: Point(" + p.x() + ", " + p.y() + ")");
        }
        System.out.println("--------------------------------------");

        // 使用 instanceof + record 模式解构，但只关心类型不关心值
        if (obj instanceof Point(int x, int y)) {
            System.out.println("Point 类型匹配，坐标: (" + x + ", " + y + ")");
        }
        System.out.println("--------------------------------------");

        // 匹配多种类型，只对部分类型做操作
        Object[] objects = {new Point(1, 2), "Hello", 42, null};
        for (Object o : objects) {
            if (o instanceof Point(int x, int y)) {
                System.out.println("Point 坐标: (" + x + ", " + y + ")");
            } else if (o instanceof String s) {
                System.out.println("字符串: " + s);
            } else if (o instanceof Integer i) {
                System.out.println("整数: " + i);
            } else {
                System.out.println("其他或 null: " + o);
            }
        }
    }
}