package org.bluebridge.section_19_jdk19.unit_02_record_pattern;

import org.junit.Test;

/**
 * JDK 19 Record 模式预览测试（JEP 405 - Record Patterns）
 *     注意：JDK 19 PREVIEW 特性，需要 --enable-preview
 *
 * 演化历程: Record 模式 JDK 19(JEP 405, 1st PREVIEW) → JDK 20(JEP 432, 2nd) → JDK 21(JEP 440, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class RecordPatternPreviewTest {

    /**
     * 测试 record 辅助类
     */
    record Point(int x, int y) {}

    /**
     * 测试嵌套 record 辅助类
     */
    record Line(Point start, Point end) {}

    /**
     * 测试 instanceof + record 模式解构
     */
    @Test
    public void testInstanceofRecordPattern_Preview() {
        Object obj = new Point(10, 20);
        // instanceof + record 模式解构
        if (obj instanceof Point(int x, int y)) {
            System.out.println("x=" + x + ", y=" + y);
        }
    }

    /**
     * 测试不同类型对象使用 record 模式匹配
     */
    @Test
    public void testMultipleRecordPatterns_Preview() {
        Object obj1 = new Point(3, 4);
        Object obj2 = "Hello";

        // 对 Point 类型进行 record 模式匹配
        if (obj1 instanceof Point(int x, int y)) {
            System.out.println("Point: x=" + x + ", y=" + y);
        }

        // 对非 record 类型对象，instanceof 模式不匹配
        if (obj2 instanceof Point(int x, int y)) {
            System.out.println("This won't be printed");
        } else {
            System.out.println("obj2 is not a Point");
        }
    }

    /**
     * 测试嵌套 record 模式解构
     */
    @Test
    public void testNestedRecordPattern_Preview() {
        Object obj = new Line(new Point(1, 2), new Point(3, 4));
        // 嵌套 record 模式解构
        if (obj instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
        }
    }

    /**
     * 测试 record 模式在 switch 中的使用（结合 switch 模式匹配）
     */
    @Test
    public void testRecordPatternInSwitch_Preview() {
        Object obj = new Point(5, 6);
        // 在 switch 中使用 record 模式
        String result = switch (obj) {
            case Point(int x, int y) -> "Point(" + x + ", " + y + ")";
            case null -> "null";
            default -> "Unknown: " + obj;
        };
        System.out.println(result);
    }

    /**
     * 测试 record 模式与守卫条件结合
     */
    @Test
    public void testRecordPatternWithGuard_Preview() {
        Object obj = new Point(15, 25);
        // record 模式结合守卫条件
        if (obj instanceof Point(int x, int y) && x > 10 && y > 20) {
            System.out.println("Point with x>10 and y>20: (" + x + ", " + y + ")");
        }
    }
}