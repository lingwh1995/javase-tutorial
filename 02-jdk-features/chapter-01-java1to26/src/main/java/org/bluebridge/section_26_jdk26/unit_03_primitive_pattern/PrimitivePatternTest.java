package org.bluebridge.section_26_jdk26.unit_03_primitive_pattern;

import org.junit.Test;

/**
 * JDK 26 基本类型模式匹配测试(STANDARD 正式特性)
 *
 * 基本类型模式匹配(Primitive Types in Patterns, JEP 488) 在 JDK 26
 * 中转正为 STANDARD 正式特性。该特性允许 int, long, double, boolean
 * 等基本类型直接参与模式匹配、instanceof 判断及 switch 分支, 无需
 * 手动装箱/拆箱和强制类型转换, 提升代码可读性和类型安全性。
 *
 * 核心能力:
 * 1. instanceof 直接匹配基本类型: obj instanceof int i
 * 2. switch 支持基本类型模式匹配: case int i -> ...
 * 3. 嵌套模式匹配中基本类型可直接使用
 * 4. 编译器进行支配性检查, 避免不可达分支
 *
 * 演化历程: 基本类型模式匹配 JDK 23(1st) → JDK 24(2nd) → JDK 25(3rd) → JDK 26(JEP 488, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class PrimitivePatternTest {

    /**
     * 测试 instanceof 直接匹配基本类型(STANDARD)
     * 使用 obj instanceof int i 语法, 判断通过后模式变量 i
     * 直接作为 int 类型使用, 无需手动拆箱
     */
    @Test
    public void testPrimitiveInstanceof() {
        System.out.println("=== instanceof 基本类型模式匹配 ===");

        // 匹配 int 类型
        Object obj1 = 42;
        if (obj1 instanceof int i) {
            System.out.println("匹配到 int 类型, 值: " + i + ", 翻倍: " + (i * 2));
        }

        // 匹配 double 类型
        Object obj2 = 3.14159;
        if (obj2 instanceof double d) {
            System.out.println("匹配到 double 类型, 值: " + d + ", 四舍五入: " + Math.round(d));
        }

        // 匹配 long 类型
        Object obj3 = 10000000000L;
        if (obj3 instanceof long l) {
            System.out.println("匹配到 long 类型, 值: " + l);
        }

        // 匹配 boolean 类型
        Object obj4 = true;
        if (obj4 instanceof boolean b) {
            System.out.println("匹配到 boolean 类型, 值: " + b + ", 取反: " + !b);
        }

        // 不匹配的情况
        Object obj5 = "Hello";
        if (obj5 instanceof int i) {
            System.out.println("不会执行: 字符串不是 int");
        } else {
            System.out.println("字符串不是 int 类型, 不会匹配");
        }
    }

    /**
     * 测试 switch 支持基本类型模式匹配(STANDARD)
     * switch 表达式和语句可以匹配基本类型模式,
     * case 分支使用 case int i -> 语法
     */
    @Test
    public void testPrimitiveSwitch() {
        System.out.println("=== switch 基本类型模式匹配 ===");

        Object obj = 42;
        String result = switch (obj) {
            case int i -> "整数类型(int), 值: " + i;
            case long l -> "长整数类型(long), 值: " + l;
            case double d -> "浮点数类型(double), 值: " + d;
            case boolean b -> "布尔类型(boolean), 值: " + b;
            case String s -> "字符串类型, 内容: " + s;
            default -> "未知类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("结果: " + result);
        System.out.println("--------------------------------------");

        // 测试 double 匹配
        Object obj2 = 2.71828;
        String result2 = switch (obj2) {
            case int i -> "整数: " + i;
            case double d -> "浮点数: " + d;
            default -> "其他";
        };
        System.out.println("double 匹配结果: " + result2);
    }

    /**
     * 测试嵌套模式匹配中基本类型的使用(STANDARD)
     * 在记录模式(Record Pattern)中, 基本类型字段可以直接匹配
     */
    @Test
    public void testNestedPrimitivePattern() {
        System.out.println("=== 嵌套模式匹配(基本类型) ===");

        // 定义记录类, 包含基本类型字段
        record Point(int x, int y) {}
        record Rectangle(Point topLeft, Point bottomRight) {}

        // 嵌套模式匹配: 解构 Rectangle 并获取内部 Point 的 int 字段
        Object obj = new Rectangle(new Point(10, 20), new Point(30, 40));
        if (obj instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            int width = x2 - x1;
            int height = y2 - y1;
            System.out.println("矩形左上角: (" + x1 + ", " + y1 + ")");
            System.out.println("矩形右下角: (" + x2 + ", " + y2 + ")");
            System.out.println("矩形宽度: " + width + ", 高度: " + height);
        }
    }

    /**
     * 测试基本类型模式匹配结合守卫条件(STANDARD)
     * 可以在模式匹配后使用 when 守卫条件添加额外过滤
     */
    @Test
    public void testPrimitivePatternWithGuard() {
        System.out.println("=== 基本类型模式匹配 + 守卫条件 ===");

        Object obj = 50;
        String result = switch (obj) {
            case int i when i > 0 && i <= 100 -> "小整数(1-100): " + i;
            case int i when i > 100 -> "大整数(&gt;100): " + i;
            case int i -> "非正数: " + i;
            case double d when d > 0 -> "正浮点数: " + d;
            case double d -> "非正浮点数: " + d;
            default -> "其他类型";
        };
        System.out.println("匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 测试多种守卫条件
        Object obj2 = -3.14;
        String result2 = switch (obj2) {
            case int i when i > 0 -> "正整数: " + i;
            case double d when d > 0 -> "正浮点数: " + d;
            case double d -> "非正浮点数: " + d;
            default -> "其他";
        };
        System.out.println("负浮点数匹配结果: " + result2);
    }

    /**
     * 测试基本类型模式匹配的支配性检查(STANDARD)
     * 编译器会检查模式匹配分支的支配性, 避免不可达分支
     * 例如, double 模式支配 int 模式(因为 int 可以隐式转换为 double)
     */
    @Test
    public void testPrimitivePatternDominance() {
        System.out.println("=== 基本类型模式匹配支配性检查 ===");

        // 注意: 在 switch 中, 更具体的类型应该放在前面
        // 这里 int 应该放在 double 前面, 否则 int 分支不可达
        Object obj = 42;
        String result = switch (obj) {
            case int i -> "精确匹配 int: " + i;
            case double d -> "匹配 double: " + d;
            default -> "其他";
        };
        System.out.println("支配性检查结果: " + result);
    }
}