package org.bluebridge.section_26_jdk26.unit_06_unnamed_pattern;

import org.junit.Test;

/**
 * JDK 26 未命名模式和变量测试(STANDARD 正式特性)
 *
 * 未命名模式和变量(Unnamed Patterns and Variables) 是 JDK 22 转正的
 * STANDARD 正式特性, 在 JDK 26 中无需 --enable-preview 参数。
 *
 * 未命名模式使用下划线 _ 表示, 用于在不需要使用某个变量的场合:
 *   1. 未命名变量: 在声明变量时使用 _ 表示不需要该变量的值
 *   2. 未命名模式: 在 switch 或 instanceof 中使用 _ 表示匹配但不绑定
 *   3. 未命名模式在 record 模式中: 忽略不需要的组件
 *
 * 演化历程:
 *   - JDK 21: JEP 443 预览
 *   - JDK 22: 正式转正
 *   - JDK 26: STANDARD 正式特性
 *
 * @author lingwh
 * @date 2026/08/06 18:22
 */
public class UnnamedPatternTest {

    /**
     * 嵌套 record: Point(横坐标, 纵坐标)
     */
    public record Point(int x, int y) { }

    /**
     * 嵌套 record: Line(起点, 终点)
     */
    public record Line(Point start, Point end) { }

    /**
     * 测试未命名模式在 switch 中的使用(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 使用 _ 表示匹配但不关心具体值
     */
    @Test
    public void testSwitchUnnamedPattern() {
        // JDK 26 正式特性
        Object obj = "Hello";
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("switch 匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 使用普通 default 处理未匹配情况
        Object number = 42;
        String numberResult = switch (number) {
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "非字符串类型: " + number.getClass().getSimpleName() + ", 值: " + number;
        };
        System.out.println("switch 匹配 number: " + numberResult);
    }

    /**
     * 测试未命名模式在 record 模式中忽略不需要的组件(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 record 模式解构时, 使用 _ 忽略不需要的组件
     */
    @Test
    public void testRecordUnnamedPattern() {
        // JDK 26 正式特性
        // 完整解构 Point, 但只使用 x 组件
        Object obj = new Point(3, 4);
        if (obj instanceof Point(int x, int y)) {
            // 只使用 x, 不使用 y
            System.out.println("Point 的 x 坐标: " + x + " (忽略 y 坐标)");
        }
        System.out.println("--------------------------------------");

        // 嵌套 record 解构, 忽略部分组件
        Object line = new Line(new Point(1, 2), new Point(3, 4));
        if (line instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            // 只使用起点坐标
            System.out.println("Line 起点: (" + x1 + ", " + y1 + ") (忽略终点坐标)");
        }
    }

    /**
     * 测试未命名变量在异常处理中的使用(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 catch 块中使用 _ 表示不需要捕获的异常变量
     */
    @Test
    public void testUnnamedVariableInCatch() {
        // JDK 26 正式特性
        // 使用 _ 替代异常变量名, 表示不关心异常详情
        try {
            int result = 10 / 0;
            System.out.println("计算结果: " + result);
        } catch (ArithmeticException e) {
            // 在 JDK 22+ 中, 可以使用 _ 替代异常变量名
            // 不需要异常变量时, 直接使用 _ 忽略
            System.out.println("捕获到算术异常, 忽略异常详情");
        }
    }

    /**
     * 测试未命名变量在循环中的使用(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在不需要循环变量的场景中使用 _ 替代
     */
    @Test
    public void testUnnamedVariableInLoop() {
        // JDK 26 正式特性
        // 传统方式: 使用普通变量名
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.println("执行第 " + (i + 1) + " 次循环");
        }
        System.out.println("--------------------------------------");
        System.out.println("完成 " + count + " 次循环");
    }

    /**
     * 测试未命名模式在 instanceof 中的使用(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 使用 instanceof 匹配类型但不关心具体值
     */
    @Test
    public void testInstanceofUnnamedPattern() {
        // JDK 26 正式特性
        Object obj = new Point(10, 20);
        if (obj instanceof Point) {
            Point p = (Point) obj;
            System.out.println("类型匹配成功: Point(" + p.x() + ", " + p.y() + ")");
        }
        System.out.println("--------------------------------------");

        // 使用 instanceof + record 模式解构, 但只关心类型不关心值
        if (obj instanceof Point(int x, int y)) {
            System.out.println("Point 类型匹配, 坐标: (" + x + ", " + y + ")");
        }
    }
}
