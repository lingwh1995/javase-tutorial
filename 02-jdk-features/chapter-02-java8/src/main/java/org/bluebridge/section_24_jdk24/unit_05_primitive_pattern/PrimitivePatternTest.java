package org.bluebridge.section_24_jdk24.unit_05_primitive_pattern;

import org.junit.Test;

/**
 * JDK 24 基本类型模式匹配测试（PREVIEW 预览特性）
 *
 * 基本类型模式匹配(JEP 488) 是 JDK 24 的 PREVIEW 预览特性，第二次预览，
 * 编译和运行都需要 --enable-preview 参数。
 *
 * 在 JDK 24 之前，instanceof 和 switch 中的模式匹配只支持引用类型。
 * 基本类型模式匹配扩展了模式匹配，使其支持基本类型（int, long, double, boolean 等）：
 *   1. instanceof 基本类型模式：obj instanceof int
 *   2. switch 基本类型模式：case int i -> ...
 *   3. 基本类型模式的守卫条件：case int i when i > 0
 *
 * 注意：本文件使用 JDK 24 PREVIEW 特性的真实语法编写，
 *       编译命令：javac --enable-preview --release 24 PrimitivePatternTest.java
 *       运行命令：java --enable-preview PrimitivePatternTest
 *
 * 演化历程: 基本类型模式匹配 JDK 23(1st) → JDK 24(JEP 488, 2nd PREVIEW) → JDK 25(3rd) → JDK 26(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:12
 */
public class PrimitivePatternTest {

    /**
     * 测试 instanceof 基本类型模式匹配（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 使用 instanceof 匹配基本类型，可以直接匹配 int, long, double 等基本类型
     */
    @Test
    public void testInstanceofPrimitivePattern_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        Object value = 42;

        // 使用 instanceof 匹配基本类型 int
        if (value instanceof int) {
            System.out.println("value 是 int 类型, 值: " + value);
        } else {
            System.out.println("value 不是 int 类型");
        }
        System.out.println("--- 分割线 ---");

        // 匹配 double 类型
        Object pi = 3.14159;
        if (pi instanceof double) {
            System.out.println("pi 是 double 类型, 值: " + pi);
        }
        System.out.println("--- 分割线 ---");

        // 匹配 boolean 类型
        Object flag = true;
        if (flag instanceof boolean) {
            System.out.println("flag 是 boolean 类型, 值: " + flag);
        }
        System.out.println("--- 分割线 ---");

        // 匹配不同的数值类型
        Object[] values = {(byte) 1, (short) 2, 3, 4L, 5.0f, 6.0};
        for (Object v : values) {
            if (v instanceof byte) {
                System.out.println(v + " 是 byte 类型");
            } else if (v instanceof short) {
                System.out.println(v + " 是 short 类型");
            } else if (v instanceof int) {
                System.out.println(v + " 是 int 类型");
            } else if (v instanceof long) {
                System.out.println(v + " 是 long 类型");
            } else if (v instanceof float) {
                System.out.println(v + " 是 float 类型");
            } else if (v instanceof double) {
                System.out.println(v + " 是 double 类型");
            }
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 switch 基本类型模式匹配（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在 switch 表达式中使用基本类型模式匹配
     */
    @Test
    public void testSwitchPrimitivePattern_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        Object value = 42;

        String result = switch (value) {
            case int i -> "匹配到 int: " + i;
            case long l -> "匹配到 long: " + l;
            case double d -> "匹配到 double: " + d;
            case String s -> "匹配到 String: " + s;
            case null -> "null 值";
            default -> "其他类型: " + value.getClass().getSimpleName();
        };
        System.out.println("switch 基本类型模式匹配结果: " + result);
        System.out.println("--- 分割线 ---");

        // 测试 double 类型
        Object pi = 3.14159;
        String piResult = switch (pi) {
            case int i -> "int: " + i;
            case double d -> "double: " + d;
            case null -> "null";
            default -> "其他: " + pi;
        };
        System.out.println("double 匹配结果: " + piResult);
        System.out.println("--- 分割线 ---");

        // 测试 boolean 类型
        Object flag = true;
        String flagResult = switch (flag) {
            case boolean b -> "boolean: " + b;
            case null -> "null";
            default -> "其他: " + flag;
        };
        System.out.println("boolean 匹配结果: " + flagResult);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 switch 基本类型模式匹配 - 综合场景（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在 switch 中混合使用基本类型和引用类型模式匹配
     */
    @Test
    public void testSwitchPrimitivePatternMixed_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        Object[] values = {
                (byte) 1,      // byte
                (short) 2,     // short
                3,              // int
                4L,             // long
                5.0f,           // float
                6.0,            // double
                true,           // boolean
                'A',            // char
                "Hello",        // String
                null            // null
        };

        for (Object value : values) {
            String result = switch (value) {
                case byte b -> "byte: " + b;
                case short s -> "short: " + s;
                case int i -> "int: " + i;
                case long l -> "long: " + l;
                case float f -> "float: " + f;
                case double d -> "double: " + d;
                case boolean b -> "boolean: " + b;
                case char c -> "char: " + c;
                case String s -> "String: " + s;
                case null -> "null";
                default -> "其他: " + value;
            };
            System.out.println(value + " -> " + result);
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 switch 基本类型模式匹配的守卫条件（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在基本类型模式匹配中使用 when 守卫条件
     */
    @Test
    public void testSwitchPrimitivePatternGuarded_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        Object[] values = {-5, 0, 10, 100, 3.14, -2.5, 0.0};

        for (Object value : values) {
            String result = switch (value) {
                case int i when i > 0 -> "正整数: " + i;
                case int i when i == 0 -> "零";
                case int i -> "负整数: " + i;
                case double d when d > 0 -> "正浮点数: " + d;
                case double d when d == 0 -> "零浮点数";
                case double d -> "负浮点数: " + d;
                case null -> "null";
                default -> "其他: " + value;
            };
            System.out.println(value + " -> " + result);
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试基本类型模式匹配与自动装箱拆箱（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 测试基本类型模式匹配在处理 Integer 和 int 时的行为
     */
    @Test
    public void testPrimitivePatternWithBoxing_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // Integer 对象可以匹配 int 模式
        Object boxedInt = Integer.valueOf(42);
        if (boxedInt instanceof int) {
            System.out.println("Integer 对象匹配 int 模式: " + boxedInt);
        }
        System.out.println("--- 分割线 ---");

        // 基本类型 int 可以匹配 Integer 模式吗？
        Object primitiveInt = 42;
        if (primitiveInt instanceof Integer) {
            System.out.println("int 值匹配 Integer 模式: " + primitiveInt);
        }
        System.out.println("--- 分割线 ---");

        // switch 中的混合模式匹配
        Object[] values = {42, Integer.valueOf(100), 3.14, "test"};
        for (Object v : values) {
            String result = switch (v) {
                case int i -> "int 模式: " + i;
                case Integer i -> "Integer 模式: " + i;
                case double d -> "double 模式: " + d;
                case String s -> "String 模式: " + s;
                case null -> "null";
                default -> "其他: " + v;
            };
            System.out.println(v + " (" + v.getClass().getSimpleName() + ") -> " + result);
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试基本类型模式匹配在 switch 语句中的使用（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 使用 switch 语句（而非表达式）进行基本类型模式匹配
     */
    @Test
    public void testSwitchPrimitivePatternStatement_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        Object value = 42;

        switch (value) {
            case int i -> System.out.println("int 值: " + i + ", 平方: " + (i * i));
            case double d -> System.out.println("double 值: " + d + ", 平方: " + (d * d));
            case String s -> System.out.println("字符串: " + s + ", 长度: " + s.length());
            case null -> System.out.println("null 值");
            default -> System.out.println("其他类型: " + value);
        }
        System.out.println("--- 分割线 ---");

        // 测试多个基本类型
        Object[] testValues = {10, 3.14, "Hello", true, null};
        for (Object v : testValues) {
            System.out.print(v + " -> ");
            switch (v) {
                case int i -> System.out.println("int, 翻倍: " + (i * 2));
                case double d -> System.out.println("double, 翻倍: " + (d * 2));
                case boolean b -> System.out.println("boolean, 取反: " + !b);
                case String s -> System.out.println("String, 大写: " + s.toUpperCase());
                case null -> System.out.println("null");
                default -> System.out.println("其他: " + v);
            }
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试基本类型模式匹配的嵌套使用（PREVIEW）
     * JDK 24 PREVIEW 特性，需要 --enable-preview
     * 在 record 模式中嵌套使用基本类型模式
     */
    @Test
    public void testPrimitivePatternNested_Preview() {
        // JDK 24 PREVIEW 特性，需要 --enable-preview
        // 定义一个简单的 record 用于测试
        record Point(int x, int y) {}
        record Rectangle(Point topLeft, Point bottomRight) {}

        // 测试嵌套的基本类型模式匹配
        Object rect = new Rectangle(new Point(10, 20), new Point(30, 40));

        if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
            System.out.println("矩形区域: 左上(" + x1 + "," + y1 + "), 右下(" + x2 + "," + y2 + ")");
            System.out.println("面积: " + area);
        }
        System.out.println("--- 分割线 ---");

        // 在 switch 中使用嵌套的基本类型模式
        Object[] shapes = {
                new Rectangle(new Point(0, 0), new Point(5, 5)),
                new Rectangle(new Point(1, 1), new Point(10, 10)),
                "not a shape"
        };

        for (Object shape : shapes) {
            String result = switch (shape) {
                case Rectangle(Point(int x1, int y1), Point(int x2, int y2))
                        when x1 >= 0 && y1 >= 0 && x2 > x1 && y2 > y1 ->
                        "有效矩形: 面积=" + ((x2 - x1) * (y2 - y1));
                case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) ->
                        "无效矩形: 坐标异常";
                case String s -> "字符串: " + s;
                case null -> "null";
                default -> "其他: " + shape;
            };
            System.out.println(shape + " -> " + result);
        }
        System.out.println("--- 分割线 ---");
    }
}