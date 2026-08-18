package org.bluebridge.section_20_jdk20.unit_02_record_pattern;

import org.junit.Test;

/**
 * JDK 20 Record 模式测试（PREVIEW 特性）
 * @see JEP 432: Record Patterns (Second Preview)
 *
 * 演化历程: Record 模式 JDK 19(1st) → JDK 20(JEP 432, 2nd) → JDK 21(JEP 440, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 02:18
 */
public class RecordPatternTest {

    /**
     * 用于测试的简单 Record 类型
     */
    record Point(int x, int y) {}

    /**
     * 用于测试的嵌套 Record 类型
     */
    record Line(Point start, Point end) {}

    /**
     * 用于测试的带泛型的 Record 类型
     */
    record Box<T>(T value) {}

    /**
     * 测试 instanceof + Record 模式解构
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testInstanceofRecordPattern_Preview() {
        Object obj = new Point(10, 20);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("testInstanceofRecordPattern_Preview: Point(" + x + ", " + y + ")");
        }
    }

    /**
     * 测试嵌套 Record 模式解构
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testNestedRecordPattern_Preview() {
        Object obj = new Line(new Point(1, 2), new Point(3, 4));
        if (obj instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("testNestedRecordPattern_Preview: Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
        }
    }

    /**
     * 测试 Switch 中 Record 模式
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testSwitchRecordPattern_Preview() {
        Object obj = new Point(5, 15);
        String result = switch (obj) {
            case Point(int x, int y) when x == y -> "Square point: (" + x + ", " + y + ")";
            case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
            case String s -> "String: " + s;
            case null -> "null value";
            default -> "Unknown";
        };
        System.out.println("testSwitchRecordPattern_Preview: " + result);
    }

    /**
     * 测试 Record 泛型模式匹配
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    @SuppressWarnings("preview")
    public void testGenericRecordPattern_Preview() {
        Object obj = new Box<>("Hello Record");
        if (obj instanceof Box<String>(var value)) {
            System.out.println("testGenericRecordPattern_Preview: Box contains string '" + value + "'");
        }
    }

    /**
     * 测试 Record 模式与 var 结合使用
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testRecordPatternWithVar_Preview() {
        Object obj = new Point(100, 200);
        if (obj instanceof Point(var x, var y)) {
            System.out.println("testRecordPatternWithVar_Preview: Point(" + x + ", " + y + ") using var");
        }
    }
}