package org.bluebridge.section_20_jdk20.unit_01_switch_pattern;

import org.junit.Test;

/**
 * JDK 20 Switch 模式匹配测试（PREVIEW 特性）
 * @see JEP 427: Pattern Matching for switch (Second Preview)
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class SwitchPatternTest {

    /**
     * 测试 Switch 类型模式匹配
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testTypePattern_Preview() {
        Object obj = "Hello, JDK 20";
        String result = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Long l -> "Long: " + l;
            case Double d -> "Double: " + d;
            case null -> "null value";
            default -> "Unknown type: " + obj.getClass().getName();
        };
        System.out.println("testTypePattern_Preview: " + result);
    }

    /**
     * 测试 Switch 守卫模式（when clause）
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testGuardedPattern_Preview() {
        Object obj = 42;
        String result = switch (obj) {
            case Integer i when i > 0 -> "Positive integer: " + i;
            case Integer i when i == 0 -> "Zero";
            case Integer i -> "Negative integer: " + i;
            case String s when s.length() > 5 -> "Long string: " + s;
            case String s -> "Short string: " + s;
            case null -> "null value";
            default -> "Unknown type";
        };
        System.out.println("testGuardedPattern_Preview: " + result);
    }

    /**
     * 测试 Switch 多类型模式匹配
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testMultiTypePattern_Preview() {
        Object obj = 3.14;
        String result = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String length: " + s.length();
            case Double d -> "Double with int value: " + d.intValue();
            case Float f -> "Float: " + f;
            case null -> "null value";
            default -> "Other type: " + obj;
        };
        System.out.println("testMultiTypePattern_Preview: " + result);
    }

    /**
     * 测试 Switch 与 null 的匹配
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testNullPattern_Preview() {
        Object obj = null;
        String result = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case null -> "Caught null value";
            default -> "Unknown";
        };
        System.out.println("testNullPattern_Preview: " + result);
    }
}