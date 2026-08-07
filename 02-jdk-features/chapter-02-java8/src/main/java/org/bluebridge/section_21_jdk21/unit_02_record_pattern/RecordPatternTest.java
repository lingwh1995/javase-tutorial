package org.bluebridge.section_21_jdk21.unit_02_record_pattern;

import org.junit.Test;

/**
 * JDK 21 Record 模式测试(STANDARD 正式特性)
 *
 * Record 模式(JEP 440) 在 JDK 21 中转正为 STANDARD 正式特性, 不再需要 --enable-preview。
 * Record 模式允许在类型匹配后自动解构(deconstruct) record 的组件, 直接访问组件值,
 * 无需手动调用访问器方法。
 *
 * Record 模式的应用场景:
 *   1. instanceof + record 模式: if (obj instanceof Point(int x, int y)) { ... }
 *   2. 嵌套 record 模式: 解构嵌套的 record 结构
 *   3. switch 中 record 模式: case Point(int x, int y) -> ...
 *
 * 演化历程: Record 模式 JDK 19(1st) → JDK 20(2nd) → JDK 21(JEP 440, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 18:56
 */
public class RecordPatternTest {

    /**
     * 嵌套 record: Point(横坐标, 纵坐标)
     */
    public record Point(int x, int y) { }

    /**
     * 嵌套 record: Line(起点, 终点), 组件为 Point 类型, 用于测试嵌套 record 模式
     */
    public record Line(Point start, Point end) { }

    /**
     * 嵌套 record: Rectangle(左上角, 右下角), 用于测试深层嵌套 record 模式
     */
    public record Rectangle(Point topLeft, Point bottomRight) { }

    /**
     * 测试 instanceof + record 模式解构(STANDARD)
     * 使用 instanceof 匹配 record 类型并同时解构组件
     * 语法: if (obj instanceof Point(int x, int y)) 直接获取 x, y
     */
    @Test
    public void testInstanceofRecordPattern() {
        Object obj = new Point(10, 20);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("instanceof 解构 Point: x = " + x + ", y = " + y);
        } else {
            System.out.println("不是 Point 类型");
        }
        System.out.println("--------------------------------------");

        // 不同类型不匹配
        Object str = "Hello";
        if (str instanceof Point(int x, int y)) {
            System.out.println("x = " + x + ", y = " + y);
        } else {
            System.out.println("字符串不是 Point 类型, 不匹配");
        }
    }

    /**
     * 测试嵌套 record 模式解构(STANDARD)
     * 当 record 的组件也是 record 时, 可以一层层解构到最内层
     * 语法: if (obj instanceof Line(Point(int x1, int y1), Point(int x2, int y2)))
     */
    @Test
    public void testNestedRecordPattern() {
        // 嵌套 record: Line 包含两个 Point 组件
        Object line = new Line(new Point(1, 2), new Point(3, 4));
        if (line instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("嵌套 record 解构 Line:");
            System.out.println("  起点: (" + x1 + ", " + y1 + ")");
            System.out.println("  终点: (" + x2 + ", " + y2 + ")");
            System.out.println("  长度: " + Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        }
        System.out.println("--------------------------------------");

        // 更深层嵌套: Rectangle 包含两个 Point
        Object rect = new Rectangle(new Point(0, 10), new Point(10, 0));
        if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("深层嵌套 record 解构 Rectangle:");
            System.out.println("  左上角: (" + x1 + ", " + y1 + ")");
            System.out.println("  右下角: (" + x2 + ", " + y2 + ")");
            int width = Math.abs(x2 - x1);
            int height = Math.abs(y2 - y1);
            System.out.println("  宽度: " + width + ", 高度: " + height + ", 面积: " + (width * height));
        }
    }

    /**
     * 测试 switch 中 record 模式(STANDARD)
     * switch 的 case 标签可以使用 record 模式解构, 配合类型模式和守卫模式一起使用
     */
    @Test
    public void testSwitchRecordPattern() {
        Object[] objects = {new Point(3, 4), new Line(new Point(0, 0), new Point(5, 5)), "Hello", null};
        for (Object obj : objects) {
            String result = switch (obj) {
                case null -> "null 值";
                case Point(int x, int y) -> "Point 坐标: (" + x + ", " + y + ")";
                case Line(Point(int x1, int y1), Point(int x2, int y2)) ->
                    "Line 从 (" + x1 + ", " + y1 + ") 到 (" + x2 + ", " + y2 + ")";
                case String s -> "字符串: " + s;
                default -> "其他类型: " + obj.getClass().getSimpleName();
            };
            System.out.println(obj + " -> " + result);
        }
    }

    /**
     * 测试 record 模式与守卫模式结合使用(STANDARD)
     * 在 switch 中使用 when 守卫条件对 record 解构后的组件进行条件过滤
     */
    @Test
    public void testRecordPatternWithGuard() {
        Object[] points = {new Point(3, 4), new Point(-1, 5), new Point(0, 0), new Point(-2, -3)};
        for (Object obj : points) {
            String result = switch (obj) {
                case Point(int x, int y) when x > 0 && y > 0 -> "第一象限: (" + x + ", " + y + ")";
                case Point(int x, int y) when x < 0 && y > 0 -> "第二象限: (" + x + ", " + y + ")";
                case Point(int x, int y) when x < 0 && y < 0 -> "第三象限: (" + x + ", " + y + ")";
                case Point(int x, int y) when x > 0 && y < 0 -> "第四象限: (" + x + ", " + y + ")";
                case Point(int x, int y) -> "原点或坐标轴上: (" + x + ", " + y + ")";
                default -> "其他";
            };
            System.out.println(result);
        }
    }

    /**
     * 测试 instanceof + record 模式 + 流程控制(STANDARD)
     * 在 if-else 分支中使用 record 模式解构, 结合条件判断
     */
    @Test
    public void testInstanceofRecordPatternWithControlFlow() {
        Object obj = new Point(5, 5);
        // if-else 中使用 record 模式
        if (obj instanceof Point(int x, int y) && x == y) {
            System.out.println("点在对角线上: (" + x + ", " + y + ")");
        } else if (obj instanceof Point(int x, int y)) {
            System.out.println("普通点: (" + x + ", " + y + ")");
        } else {
            System.out.println("不是 Point 类型");
        }
    }
}