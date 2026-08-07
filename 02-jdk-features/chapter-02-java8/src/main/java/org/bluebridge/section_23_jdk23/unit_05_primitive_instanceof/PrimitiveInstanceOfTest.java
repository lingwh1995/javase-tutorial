package org.bluebridge.section_23_jdk23.unit_05_primitive_instanceof;

import org.junit.Test;

/**
 * JDK 23 基本类型 instanceof 测试（PREVIEW 预览特性）
 *
 * 基本类型 instanceof(Primitive Types in Patterns, instanceof, and switch, JEP 455)
 * 是 JDK 23 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 本类专注于测试 instanceof 与基本类型的结合使用:
 *   1. 传统 instanceof 只能用于引用类型, 基本类型需要先装箱
 *   2. JDK 23 允许直接在 instanceof 中使用基本类型模式
 *   3. 基本类型 instanceof 与 switch 模式匹配结合使用
 *   4. 支持所有基本类型: byte, short, int, long, float, double, char, boolean
 *
 * 注意: 本文件使用 JDK 23 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 23 PrimitiveInstanceOfTest.java
 *       运行命令: java --enable-preview PrimitiveInstanceOfTest
 *
 * 演化历程: 基本类型模式匹配 JDK 23(JEP 455, 1st PREVIEW) → JDK 24(JEP 488, 2nd) → JDK 25(3rd) → JDK 26(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class PrimitiveInstanceOfTest {

    /**
     * 测试基本类型 instanceof 基本用法(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 使用 obj instanceof int i 直接匹配基本类型
     */
    @Test
    public void testBasicPrimitiveInstanceOf_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object obj = 42;

        // 直接匹配基本类型 int
        if (obj instanceof int i) {
            System.out.println("对象是 int 类型, 值: " + i + ", 翻倍: " + (i * 2));
        } else {
            System.out.println("对象不是 int 类型");
        }
        System.out.println("--------------------------------------");

        // 匹配 double 类型
        Object doubleObj = 3.14159;
        if (doubleObj instanceof double d) {
            System.out.println("对象是 double 类型, 值: " + d + ", 四舍五入: " + Math.round(d));
        }
        System.out.println("--------------------------------------");

        // 匹配 boolean 类型
        Object boolObj = true;
        if (boolObj instanceof boolean b) {
            System.out.println("对象是 boolean 类型, 值: " + b + ", 取反: " + !b);
        }
    }

    /**
     * 测试基本类型 instanceof 与类型转换的对比(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 对比传统装箱方式和 JDK 23 基本类型模式的差异
     */
    @Test
    public void testPrimitiveInstanceOfVsBoxing_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 传统方式 vs JDK 23 基本类型 instanceof =====");
        System.out.println();

        // 传统方式: 需要先判断是否为包装类, 再拆箱
        Object obj = 42;
        System.out.println("传统方式 (JDK 22 及之前):");
        if (obj instanceof Integer) {
            int value = (Integer) obj;  // 需要强制转换和拆箱
            System.out.println("  匹配到 int, 值: " + value + " (需要 instanceof Integer 再拆箱)");
        }

        System.out.println("JDK 23 方式:");
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        if (obj instanceof int i) {
            // 直接匹配基本类型, 无需拆箱
            System.out.println("  匹配到 int, 值: " + i + " (直接 instanceof int, 无需拆箱)");
        }
        System.out.println("--------------------------------------");
        System.out.println("JDK 23 优势: 语法更简洁, 不需要中间包装类判断");
    }

    /**
     * 测试基本类型 instanceof 与 switch 结合使用(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 switch 中使用 case int i, case double d 等基本类型模式
     */
    @Test
    public void testPrimitiveInstanceOfWithSwitch_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object[] objects = {42, 3.14, 100L, 3.14f, true, 'A', (byte) 127, (short) 32767, null, "Hello"};

        for (Object obj : objects) {
            // 使用 instanceof 判断后再使用 switch
            String description;
            if (obj instanceof int i) {
                description = "int 类型, 值: " + i + ", 绝对值: " + Math.abs(i);
            } else if (obj instanceof double d) {
                description = "double 类型, 值: " + d + ", 取整: " + (int) d;
            } else if (obj instanceof long l) {
                description = "long 类型, 值: " + l;
            } else if (obj instanceof float f) {
                description = "float 类型, 值: " + f;
            } else if (obj instanceof boolean b) {
                description = "boolean 类型, 值: " + b;
            } else if (obj instanceof char c) {
                description = "char 类型, 值: '" + c + "', Unicode: " + (int) c;
            } else if (obj instanceof byte b) {
                description = "byte 类型, 值: " + b;
            } else if (obj instanceof short s) {
                description = "short 类型, 值: " + s;
            } else if (obj instanceof String s) {
                description = "String 类型, 长度: " + s.length() + ", 内容: " + s;
            } else {
                description = "null 或其他类型";
            }
            System.out.println("  [" + obj + "] -> " + description);
        }
    }

    /**
     * 测试基本类型 instanceof 在 switch 表达式中的使用(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 使用 switch 表达式直接匹配基本类型模式
     */
    @Test
    public void testPrimitiveInstanceOfSwitchExpression_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object[] testValues = {42, 3.14, true, 'Z', "Hello JDK 23", null, 100L};

        for (Object obj : testValues) {
            String result = switch (obj) {
                case int i -> "基本类型 int: " + i + " (二进制: " + Integer.toBinaryString(i) + ")";
                case double d -> "基本类型 double: " + d + " (科学计数法: " + String.format("%e", d) + ")";
                case long l -> "基本类型 long: " + l;
                case float f -> "基本类型 float: " + f;
                case boolean b -> "基本类型 boolean: " + b;
                case char c -> "基本类型 char: '" + c + "'";
                case byte b -> "基本类型 byte: " + b;
                case short s -> "基本类型 short: " + s;
                case String str -> "引用类型 String: \"" + str + "\" (长度: " + str.length() + ")";
                case null -> "null 值";
                default -> "其他类型: " + (obj != null ? obj.getClass().getSimpleName() : "null");
            };
            System.out.println("  [" + obj + "] -> " + result);
        }
    }

    /**
     * 测试基本类型 instanceof 与守卫条件结合(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 switch 中结合基本类型模式和 when 守卫条件
     */
    @Test
    public void testPrimitiveInstanceOfWithGuard_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object[] testValues = {42, -10, 0, 3.14, -2.5, 0.0, 100L, -100L};

        for (Object obj : testValues) {
            String result = switch (obj) {
                case int i when i > 0 -> "正整数: " + i;
                case int i when i == 0 -> "零";
                case int i -> "负整数: " + i;
                case double d when d > 0 -> "正浮点数: " + d;
                case double d when d == 0.0 -> "浮点数零";
                case double d -> "负浮点数: " + d;
                case long l when l > 0 -> "正长整数: " + l;
                case long l -> "负长整数: " + l;
                case null -> "null";
                default -> "其他: " + obj;
            };
            System.out.println("  [" + obj + "] -> " + result);
        }
    }

    /**
     * 测试基本类型 instanceof 的数值范围匹配(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 不同类型的基本类型在模式匹配中的顺序和范围
     */
    @Test
    public void testPrimitiveInstanceOfNumericRange_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 基本类型数值范围匹配 =====");
        System.out.println("注意: 基本类型模式匹配依据的是实际类型, 而不是值范围");
        System.out.println();

        // 不同类型的数值, 演示类型精确匹配
        Object[] numbers = {
            (byte) 42,       // byte
            (short) 42,      // short
            42,              // int
            42L,             // long
            42.0f,           // float
            42.0             // double
        };

        for (Object num : numbers) {
            String result = switch (num) {
                case byte b -> "byte: " + b;
                case short s -> "short: " + s;
                case int i -> "int: " + i;
                case long l -> "long: " + l;
                case float f -> "float: " + f;
                case double d -> "double: " + d;
                case null -> "null";
                default -> "其他: " + num.getClass().getSimpleName();
            };
            System.out.println("  " + num.getClass().getSimpleName() + "(" + num + ") -> " + result);
        }
    }
}