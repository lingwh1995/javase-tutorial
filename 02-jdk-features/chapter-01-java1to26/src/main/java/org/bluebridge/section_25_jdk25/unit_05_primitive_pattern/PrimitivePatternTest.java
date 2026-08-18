package org.bluebridge.section_25_jdk25.unit_05_primitive_pattern;

import org.junit.Test;

/**
 * JDK 25 基本类型模式匹配测试(PREVIEW 预览特性)
 *
 * 基本类型模式匹配(Primitive Types in Patterns, instanceof, and switch,
 * JEP 488) 是 JDK 25 的 PREVIEW 预览特性, 第三次预览,
 * 编译和运行都需要 --enable-preview 参数。
 *
 * 在 JDK 25 之前, instanceof 和 switch 中的模式匹配只能用于引用类型。
 * 基本类型(如 int, long, double 等)必须通过对应的包装类型(如 Integer,
 * Long, Double 等)才能参与模式匹配。
 *
 * JDK 25 的 JEP 488 允许在以下场景中直接使用基本类型进行模式匹配:
 *   1. instanceof 中的基本类型模式: if (x instanceof int i) { ... }
 *   2. switch 中的基本类型模式: switch (x) { case int i -> ... }
 *   3. 基本类型模式与守卫条件结合: case int i when i > 0 -> ...
 *
 * 注意: 本文件使用 JDK 25 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 25 PrimitivePatternTest.java
 *       运行命令: java --enable-preview PrimitivePatternTest
 *
 * 演化历程: 基本类型模式匹配 JDK 23(1st) → JDK 24(2nd) → JDK 25(3rd PREVIEW) → JDK 26(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:12
 */
public class PrimitivePatternTest {

    /**
     * 测试 instanceof 中的基本类型模式(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 使用 instanceof int i, instanceof double d 等直接匹配基本类型值
     */
    @Test
    public void testInstanceofPrimitivePattern_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object value = 42;

        // 基本类型模式匹配
        if (value instanceof int i) {
            System.out.println("匹配到 int 值: " + i);
        } else {
            System.out.println("不是 int 类型");
        }
        System.out.println("--------------------------------------");

        // 不同类型的值
        Object doubleVal = 3.14;
        if (doubleVal instanceof double d) {
            System.out.println("匹配到 double 值: " + d);
        }
        System.out.println("--------------------------------------");

        // 综合测试多种基本类型
        Object[] values = {42, 3.14f, 3.14159, true, (long) 12345678901L, "hello"};
        for (Object v : values) {
            String typeName;
            if (v instanceof int i) {
                typeName = "int: " + i;
            } else if (v instanceof long l) {
                typeName = "long: " + l;
            } else if (v instanceof float f) {
                typeName = "float: " + f;
            } else if (v instanceof double d) {
                typeName = "double: " + d;
            } else if (v instanceof boolean b) {
                typeName = "boolean: " + b;
            } else {
                typeName = "其他类型: " + v;
            }
            System.out.println("值 " + v + " 的类型: " + typeName);
        }
    }

    /**
     * 测试 switch 中的基本类型模式(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 在 switch 中使用 case int i, case double d 等语法
     */
    @Test
    public void testSwitchPrimitivePattern_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object value = 100;

        // switch 中的基本类型模式
        String result = switch (value) {
            case int i -> "整数: " + i;
            case long l -> "长整数: " + l;
            case float f -> "浮点数(float): " + f;
            case double d -> "浮点数(double): " + d;
            case boolean b -> "布尔值: " + b;
            case null -> "null 值";
            default -> "其他类型: " + value;
        };
        System.out.println("switch 基本类型模式结果: " + result);
        System.out.println("--------------------------------------");

        // 测试不同类型值
        Object[] testValues = {42, 3.14f, 2.71828, true, 999L, null, "test"};
        for (Object val : testValues) {
            String switchResult = switch (val) {
                case int i -> "int: " + i;
                case long l -> "long: " + l;
                case float f -> "float: " + f;
                case double d -> "double: " + d;
                case boolean b -> "boolean: " + b;
                case null -> "null";
                case String s -> "String: " + s;
                default -> "其他: " + val;
            };
            System.out.println("  " + val + " -> " + switchResult);
        }
    }

    /**
     * 测试基本类型模式与守卫条件结合(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 使用 case int i when i > 0 守卫条件过滤匹配
     */
    @Test
    public void testPrimitivePatternWithGuard_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object[] values = {-5, 0, 10, 3.14, -2.5f, 100L};

        for (Object val : values) {
            String result = switch (val) {
                case int i when i > 0 -> "正整数: " + i;
                case int i -> "非正整数: " + i;
                case long l when l > 0 -> "正长整数: " + l;
                case long l -> "非正长整数: " + l;
                case float f when f > 0 -> "正浮点数(float): " + f;
                case float f -> "非正浮点数(float): " + f;
                case double d when d > 0 -> "正浮点数(double): " + d;
                case double d -> "非正浮点数(double): " + d;
                case null -> "null";
                default -> "其他: " + val;
            };
            System.out.println("  " + val + " -> " + result);
        }
    }

    /**
     * 测试基本类型模式在表达式计算中的应用(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 利用基本类型模式进行类型安全的数值计算
     */
    @Test
    public void testPrimitivePatternInCalculation_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object[] numbers = {10, 20L, 3.5f, 7.25, (byte) 5, (short) 15};

        for (Object num : numbers) {
            // 使用基本类型模式进行统一的数值处理
            double doubled = switch (num) {
                case int i -> i * 2.0;
                case long l -> l * 2.0;
                case float f -> f * 2.0;
                case double d -> d * 2.0;
                case byte b -> b * 2.0;
                case short s -> s * 2.0;
                case null -> Double.NaN;
                default -> Double.NaN;
            };
            System.out.println("  " + num + " (" + num.getClass().getSimpleName() + ") * 2 = " + doubled);
        }
    }

    /**
     * 测试基本类型模式与 instanceof 守卫条件结合(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 在 if-else 中使用 instanceof 基本类型模式配合守卫条件
     */
    @Test
    public void testInstanceofPrimitiveWithGuard_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object value = 50;

        // 使用 instanceof 基本类型模式 + 守卫条件
        if (value instanceof int i && i > 0) {
            System.out.println("正整数: " + i);
        } else if (value instanceof int i) {
            System.out.println("非正整数: " + i);
        } else {
            System.out.println("其他类型: " + value);
        }
        System.out.println("--------------------------------------");

        // 测试不同类型的值
        Object[] testValues = {42, -10, 3.14, 0L};
        for (Object val : testValues) {
            if (val instanceof int i) {
                System.out.println(val + " 是 int 类型, 值: " + i);
            } else if (val instanceof long l) {
                System.out.println(val + " 是 long 类型, 值: " + l);
            } else if (val instanceof double d) {
                System.out.println(val + " 是 double 类型, 值: " + d);
            } else {
                System.out.println(val + " 是其他类型");
            }
        }
    }

    /**
     * 测试基本类型模式在复杂条件逻辑中的应用(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 结合多种基本类型模式处理复杂的条件分支
     */
    @Test
    public void testComplexPrimitivePatternLogic_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        // 模拟几何图形的面积计算, 输入可以是各种数值类型
        Object[] inputs = {5, 3.0, 4L, 2.5f, "invalid"};

        for (Object input : inputs) {
            String description = switch (input) {
                case int i when i > 0 -> "正方形边长 " + i + ", 面积 = " + (i * i);
                case int i -> "无效边长(非正数): " + i;
                case double d when d > 0 -> "圆形半径 " + d + ", 面积 = " + (Math.PI * d * d);
                case double d -> "无效半径(非正数): " + d;
                case long l when l > 0 -> "正方形边长 " + l + ", 面积 = " + (l * l);
                case long l -> "无效边长(非正数): " + l;
                case float f when f > 0 -> "圆形半径 " + f + ", 面积 = " + (Math.PI * f * f);
                case float f -> "无效半径(非正数): " + f;
                case null -> "输入为 null";
                default -> "不支持的输入类型: " + input.getClass().getSimpleName();
            };
            System.out.println("  输入: " + input + " -> " + description);
        }
    }

    /**
     * 测试基本类型模式与类型转换(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 基本类型模式匹配后自动绑定变量为对应基本类型, 不需要手动转换
     */
    @Test
    public void testPrimitivePatternTypeConversion_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Object[] values = {42, 3.14, 100L, 2.5f};

        for (Object val : values) {
            // 在 switch 中匹配基本类型, 自动绑定变量, 无需强制转换
            String classification = switch (val) {
                case int i -> {
                    // i 已经是 int 类型, 可以直接用于算术运算
                    if (i > 0) yield "正整数(范围: " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE + ")";
                    else yield "非正整数";
                }
                case double d -> {
                    if (d == Math.floor(d)) yield "整数值的 double: " + (long) d;
                    else yield "小数 double: " + d;
                }
                case long l -> "长整数(范围: " + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE + ")";
                case float f -> "浮点数 float: " + f;
                case null -> "null";
                default -> "其他类型: " + val.getClass().getSimpleName();
            };
            System.out.println("  " + val + " -> " + classification);
        }
    }
}