package org.bluebridge.section_23_jdk23.unit_01_primitive_pattern;

import org.junit.Test;

/**
 * JDK 23 基本类型模式匹配测试（PREVIEW 预览特性）
 *
 * 基本类型模式匹配(Primitive Types in Patterns, instanceof, and switch, JEP 455) 是 JDK 23
 * 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 此前 instanceof 和 switch 模式匹配只能用于引用类型, 匹配基本类型时需要通过包装类
 * (如 Integer, Long 等) 进行自动装箱。JDK 23 允许直接在模式匹配中使用基本类型:
 *   1. instanceof 中直接使用基本类型模式, 如 if (obj instanceof int i)
 *   2. switch 中直接使用基本类型模式, 如 case int i -> ...
 *   3. 支持所有基本类型: byte, short, int, long, float, double, char, boolean
 *
 * 注意: 本文件使用 JDK 23 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 23 PrimitivePatternTest.java
 *       运行命令: java --enable-preview PrimitivePatternTest
 *
 * 演化历程: 基本类型模式匹配 JDK 23(JEP 455, 1st PREVIEW) → JDK 24(JEP 488, 2nd) → JDK 25(3rd) → JDK 26(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:09
 */
public class PrimitivePatternTest {

    /**
     * 测试在 instanceof 中使用基本类型模式(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 使用 if (obj instanceof int i) 直接匹配基本类型 int
     */
    @Test
    public void testInstanceofPrimitivePattern_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object obj = 42;

        // 直接匹配基本类型 int
        if (obj instanceof int i) {
            System.out.println("匹配到 int 类型, 值: " + i + ", 可以执行算术运算: " + (i * 2));
        } else {
            System.out.println("不是 int 类型");
        }
        System.out.println("--------------------------------------");

        // 匹配 double 类型
        Object doubleObj = 3.14;
        if (doubleObj instanceof double d) {
            System.out.println("匹配到 double 类型, 值: " + d + ", 精度保留两位: " + String.format("%.2f", d));
        }
        System.out.println("--------------------------------------");

        // 匹配 boolean 类型
        Object boolObj = true;
        if (boolObj instanceof boolean b) {
            System.out.println("匹配到 boolean 类型, 值: " + b + ", 取反: " + !b);
        }
    }

    /**
     * 测试在 switch 中使用基本类型模式(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 switch 表达式中直接使用 case int i, case long l 等基本类型模式
     */
    @Test
    public void testSwitchPrimitivePattern_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object obj = 42;

        String result = switch (obj) {
            case int i -> "匹配到 int: " + i + " (平方: " + (i * i) + ")";
            case long l -> "匹配到 long: " + l;
            case double d -> "匹配到 double: " + d;
            case float f -> "匹配到 float: " + f;
            case null -> "null 值";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("switch 基本类型模式匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 匹配 double 值
        Object doubleObj = 3.14159;
        String doubleResult = switch (doubleObj) {
            case int i -> "int: " + i;
            case double d -> "double: " + d + " (取整: " + (int) d + ")";
            case float f -> "float: " + f;
            case null -> "null";
            default -> "其他: " + doubleObj;
        };
        System.out.println("double 匹配结果: " + doubleResult);
    }

    /**
     * 测试在 switch 中使用基本类型模式配合守卫条件(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 基本类型模式可以与 when 守卫条件结合使用
     */
    @Test
    public void testSwitchPrimitivePatternWithGuard_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object obj = 100;

        String result = switch (obj) {
            case int i when i > 0 -> "正整数: " + i;
            case int i when i == 0 -> "零";
            case int i -> "负整数: " + i;
            case double d when d > 0 -> "正浮点数: " + d;
            case double d -> "非正浮点数: " + d;
            case null -> "null";
            default -> "其他: " + obj;
        };
        System.out.println("带守卫条件的匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 使用负数测试
        Object negativeObj = -50;
        String negativeResult = switch (negativeObj) {
            case int i when i > 0 -> "正整数: " + i;
            case int i when i == 0 -> "零";
            case int i -> "负整数: " + i;
            case null -> "null";
            default -> "其他: " + negativeObj;
        };
        System.out.println("负数匹配结果: " + negativeResult);
    }

    /**
     * 测试所有基本类型在模式匹配中的使用(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 演示 byte, short, int, long, float, double, char, boolean 全部基本类型
     */
    @Test
    public void testAllPrimitiveTypes_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 测试各种基本类型模式匹配 =====");

        // byte 类型
        Object byteObj = (byte) 127;
        if (byteObj instanceof byte b) {
            System.out.println("byte 值: " + b + ", 最大值: " + Byte.MAX_VALUE);
        }

        // short 类型
        Object shortObj = (short) 32767;
        if (shortObj instanceof short s) {
            System.out.println("short 值: " + s + ", 最大值: " + Short.MAX_VALUE);
        }

        // char 类型
        Object charObj = 'A';
        if (charObj instanceof char c) {
            System.out.println("char 值: " + c + ", Unicode: " + (int) c);
        }

        // boolean 类型
        Object booleanObj = false;
        if (booleanObj instanceof boolean b) {
            System.out.println("boolean 值: " + b + ", 取反: " + !b);
        }

        // 综合 switch 测试
        Object[] objects = {42, 3.14f, 3.14159, 100L, (short) 10, (byte) 1, 'X', true, null, "Hello"};
        for (Object obj : objects) {
            String switchResult = switch (obj) {
                case byte b -> "byte: " + b;
                case short s -> "short: " + s;
                case int i -> "int: " + i;
                case long l -> "long: " + l;
                case float f -> "float: " + f;
                case double d -> "double: " + d;
                case char c -> "char: " + c;
                case boolean b -> "boolean: " + b;
                case null -> "null";
                default -> "其他类型: " + (obj != null ? obj.getClass().getSimpleName() : "null");
            };
            System.out.println("  [" + obj + "] -> " + switchResult);
        }
    }

    /**
     * 测试基本类型模式与引用类型模式混合使用(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在同一个 switch 中混合使用基本类型模式和引用类型模式
     */
    @Test
    public void testMixedPrimitiveAndReferencePattern_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        Object[] objects = {42, "Hello", 3.14, List.of(1, 2, 3), null, 100L};
        for (Object obj : objects) {
            String result = switch (obj) {
                case int i -> "基本类型 int: " + i;
                case long l -> "基本类型 long: " + l;
                case double d -> "基本类型 double: " + d;
                case String s -> "引用类型 String: " + s;
                case List<?> list -> "引用类型 List: " + list.size() + " 个元素";
                case null -> "null 值";
                default -> "其他类型: " + obj.getClass().getSimpleName();
            };
            System.out.println("  [" + obj + "] -> " + result);
        }
    }
}