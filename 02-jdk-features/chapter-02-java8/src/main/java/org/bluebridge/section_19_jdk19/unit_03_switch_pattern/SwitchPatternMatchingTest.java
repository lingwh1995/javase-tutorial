package org.bluebridge.section_19_jdk19.unit_03_switch_pattern;

import org.junit.Test;

/**
 * JDK 19 Switch 模式匹配预览测试（JEP 427 - Pattern Matching for Switch）
 *     注意：JDK 19 PREVIEW 特性，需要 --enable-preview
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class SwitchPatternMatchingTest {

    /**
     * 测试 switch 类型模式匹配 - 基本用法
     */
    @Test
    public void testTypePattern_Preview() {
        Object obj = 42;
        // 使用 switch 类型模式匹配，case 后直接跟类型模式
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case Double d -> "浮点数: " + d;
            case null -> "null";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println(result);
    }

    /**
     * 测试 switch 守卫模式（使用 when 关键字）
     *     JDK 19 使用 when 关键字替代之前的 && 守卫条件
     */
    @Test
    public void testGuardedPattern_Preview() {
        Object obj = 42;
        // 使用 when 关键字添加守卫条件
        String result = switch (obj) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i when i == 0 -> "零";
            case Integer i -> "负整数: " + i;
            case String s when s.length() > 5 -> "长字符串: " + s;
            case String s -> "短字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println(result);
    }

    /**
     * 测试 switch 守卫模式 - 多种类型混合守卫条件
     */
    @Test
    public void testMixedGuardedPattern_Preview() {
        // 测试整数
        Object obj1 = -5;
        String result1 = switch (obj1) {
            case Integer i when i > 100 -> "大整数";
            case Integer i when i > 0 -> "正整数";
            case Integer i when i == 0 -> "零";
            case Integer i -> "负整数";
            case null -> "null";
            default -> "其他";
        };
        System.out.println("obj1(" + obj1 + "): " + result1);

        // 测试字符串
        Object obj2 = "Hello, JDK 19!";
        String result2 = switch (obj2) {
            case String s when s.isEmpty() -> "空字符串";
            case String s when s.length() > 10 -> "长字符串: " + s;
            case String s -> "短字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("obj2(" + obj2 + "): " + result2);

        // 测试 null
        Object obj3 = null;
        String result3 = switch (obj3) {
            case Integer i when i > 0 -> "正整数";
            case String s -> "字符串";
            case null -> "null";
            default -> "其他";
        };
        System.out.println("obj3(" + obj3 + "): " + result3);
    }

    /**
     * 测试 switch 模式匹配与枚举类型结合
     */
    @Test
    public void testEnumWithPattern_Preview() {
        // 测试枚举类型也能使用模式匹配
        Object obj = Size.LARGE;
        String result = switch (obj) {
            case Size.SMALL -> "小号";
            case Size.MEDIUM -> "中号";
            case Size.LARGE -> "大号";
            case Size.EXTRA_LARGE -> "加大号";
            case null -> "null";
            default -> "其他";
        };
        System.out.println("Size: " + result);
    }

    /**
     * 测试 switch 模式匹配 - 复杂业务逻辑场景
     */
    @Test
    public void testComplexBusinessLogic_Preview() {
        // 模拟不同类型的输入数据
        Object[] inputs = {100, "error", 0, "success", null, -50, "warning"};

        for (Object input : inputs) {
            String response = switch (input) {
                case Integer code when code > 0 -> "状态码: " + code + " (正常)";
                case Integer code when code == 0 -> "状态码: 0 (未知)";
                case Integer code -> "状态码: " + code + " (错误)";
                case String msg when "success".equals(msg) -> "操作成功";
                case String msg when "error".equals(msg) || "warning".equals(msg) -> "操作" + msg;
                case String msg -> "消息: " + msg;
                case null -> "输入为空";
                default -> "无法识别的输入: " + input;
            };
            System.out.println("输入 [" + input + "] -> " + response);
        }
    }

    /**
     * 测试 switch 模式匹配中 null 的处理
     *     JDK 19 支持在 switch 中用 case null 显式处理 null
     */
    @Test
    public void testNullHandling_Preview() {
        Object obj = null;
        // 在 JDK 19 之前，switch 传入 null 会抛出 NullPointerException
        // JDK 19 支持 case null 显式处理 null 值
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null 值";
            default -> "其他";
        };
        System.out.println("null 处理结果: " + result);
    }

    /**
     * 测试 switch 守卫模式 - 数字范围判断
     */
    @Test
    public void testNumberRangeGuardedPattern_Preview() {
        Object[] numbers = {150, 50, 0, -10, 3.14, "abc"};

        for (Object num : numbers) {
            String description = switch (num) {
                case Integer i when i > 100 -> "大整数 (>100): " + i;
                case Integer i when i > 0 && i <= 100 -> "中整数 (1-100): " + i;
                case Integer i when i == 0 -> "零";
                case Integer i -> "负整数: " + i;
                case Double d when d > 0 -> "正浮点数: " + d;
                case Double d -> "非正浮点数: " + d;
                case String s -> "非数字类型: " + s;
                case null -> "null";
                default -> "未知类型: " + num;
            };
            System.out.println("数值 [" + num + "] -> " + description);
        }
    }

    /**
     * 测试辅助枚举
     */
    enum Size {
        SMALL, MEDIUM, LARGE, EXTRA_LARGE
    }
}