package org.bluebridge.section_21_jdk21.unit_01_switch_pattern;

import org.junit.Test;

/**
 * JDK 21 Switch 模式匹配测试(STANDARD 正式特性)
 *
 * Switch 模式匹配(JEP 441) 在 JDK 21 中转正为 STANDARD 正式特性, 不再需要 --enable-preview。
 * 相比 JDK 17 的 PREVIEW 版本, JDK 21 转正后的主要变化:
 *   1. 守卫模式语法从 &amp;&amp; 改为 when 关键字
 *   2. 支持 record 模式作为 case 标签
 *   3. 类型模式、null 处理等行为保持不变
 *
 * 本类演示 JDK 21 转正后的 switch 模式匹配全部特性:
 *   1. 类型模式: case Integer i -> 匹配时自动绑定变量
 *   2. record 模式: case Point(int x, int y) -> 解构 record 组件
 *   3. 守卫模式: case Integer i when i > 0 -> 添加守卫条件
 *   4. null 处理: case null -> 显式匹配 null
 *   5. 枚举类型模式匹配: case Color.RED -> 匹配枚举常量
 *
 * @author lingwh
 * @date 2026/08/05 18:56
 */
public class SwitchPatternTest {

    /**
     * 嵌套 record: Point, 用于测试 switch 中的 record 模式
     * 定义在类内部作为静态嵌套类型
     */
    public record Point(int x, int y) { }

    /**
     * 枚举: Color, 用于测试 switch 枚举类型模式匹配
     */
    public enum Color { RED, GREEN, BLUE, YELLOW }

    /**
     * 测试 switch 类型模式(STANDARD)
     * case Integer i -> 匹配时直接将 i 绑定为 Integer 类型, 不需要强制类型转换
     * case String s -> 匹配时直接将 s 绑定为 String 类型
     * 这是 JDK 21 正式特性, 不需要 --enable-preview
     */
    @Test
    public void testSwitchTypePattern() {
        Object obj = "Hello JDK21";
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("类型模式匹配结果: " + result);
        System.out.println("--------------------------------------");

        Object number = 100;
        String numberResult = switch (number) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("整数 selector 匹配结果: " + numberResult);
    }

    /**
     * 测试 switch record 模式(STANDARD)
     * case Point(int x, int y) -> 解构 record 组件, 直接获取 x 和 y 的值
     * 这是 JDK 21 转正后的新特性, 不需要 --enable-preview
     */
    @Test
    public void testSwitchRecordPattern() {
        Object obj = new Point(10, 20);
        String result = switch (obj) {
            case Point(int x, int y) -> "坐标: (" + x + ", " + y + ")";
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("record 模式匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 不同类型的 selector 进入不同分支
        Object str = "Hello";
        String strResult = switch (str) {
            case Point(int x, int y) -> "坐标: (" + x + ", " + y + ")";
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("字符串 selector 匹配结果: " + strResult);
    }

    /**
     * 测试 switch 守卫模式(STANDARD, JDK 21 使用 when 关键字)
     * JDK 21 转正后守卫模式从 &amp;&amp; 改为 when 关键字
     * case Integer i when i > 0 -> 类型模式满足且守卫条件成立时才匹配
     * 守卫模式必须写在普通类型模式之前, 否则普通模式会先匹配
     */
    @Test
    public void testSwitchGuardedPattern() {
        Object positive = 100;
        String positiveResult = switch (positive) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null";
            default -> "其他: " + positive;
        };
        System.out.println("正数匹配结果: " + positiveResult);
        System.out.println("--------------------------------------");

        Object negative = -100;
        String negativeResult = switch (negative) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null";
            default -> "其他: " + negative;
        };
        System.out.println("负数匹配结果: " + negativeResult);
        System.out.println("--------------------------------------");

        // 守卫模式结合 record 模式: 匹配 x > 0 的 Point
        Object point = new Point(5, 10);
        String pointResult = switch (point) {
            case Point(int x, int y) when x > 0 -> "x 为正的坐标: (" + x + ", " + y + ")";
            case Point(int x, int y) -> "其他坐标: (" + x + ", " + y + ")";
            case null -> "null";
            default -> "其他";
        };
        System.out.println("守卫 + record 模式匹配结果: " + pointResult);
    }

    /**
     * 测试 switch 对 null 的处理(STANDARD)
     * case null -> 显式匹配 null, 如果 selector 为 null 且没有 case null 分支,
     * switch 表达式会抛出 NullPointerException
     */
    @Test
    public void testSwitchNullHandling() {
        Object obj = null;
        String result = switch (obj) {
            case null -> "匹配到 null";
            case String s -> "字符串: " + s;
            default -> "其他";
        };
        System.out.println("null 处理结果: " + result);
    }

    /**
     * 测试 switch 语句形式的模式匹配(STANDARD)
     * 模式匹配同样适用于 switch 语句(statement), 通过 -> 箭头语法绑定模式变量
     */
    @Test
    public void testSwitchStatement() {
        Object obj = 42;
        switch (obj) {
            case String s -> System.out.println("字符串: " + s);
            case Integer i -> System.out.println("整数: " + i);
            case null -> System.out.println("null");
            default -> System.out.println("其他: " + obj);
        }
    }

    /**
     * 测试 switch 枚举类型模式匹配(STANDARD)
     * 枚举类型在 switch 中可以直接匹配枚举常量
     * 配合 when 守卫模式可以进一步过滤
     */
    @Test
    public void testSwitchEnumPattern() {
        Color color = Color.RED;
        String result = switch (color) {
            case RED -> "红色";
            case GREEN -> "绿色";
            case BLUE -> "蓝色";
            case YELLOW -> "黄色";
        };
        System.out.println("枚举匹配结果: " + result);
        System.out.println("--------------------------------------");

        // 枚举类型作为 Object 时的类型模式匹配
        Object obj = Color.GREEN;
        String objResult = switch (obj) {
            case Color c when c == Color.RED -> "红色(枚举)";
            case Color c -> "其他颜色: " + c;
            case null -> "null";
            default -> "其他类型";
        };
        System.out.println("枚举类型模式匹配结果: " + objResult);
    }

    /**
     * 测试 switch 类型模式 + record 模式 + 守卫模式综合使用(STANDARD)
     * 在一个 switch 中混合使用多种模式
     */
    @Test
    public void testSwitchMixedPatterns() {
        Object[] objects = {null, "Hello", 42, -10, new Point(3, 4), new Point(-1, 2), 3.14};
        for (Object obj : objects) {
            String result = switch (obj) {
                case null -> "null 值";
                case Point(int x, int y) when x > 0 && y > 0 -> "第一象限坐标: (" + x + ", " + y + ")";
                case Point(int x, int y) -> "其他坐标: (" + x + ", " + y + ")";
                case Integer i when i > 0 -> "正整数: " + i;
                case Integer i -> "非正整数: " + i;
                case String s -> "字符串: " + s;
                default -> "其他类型: " + obj.getClass().getSimpleName();
            };
            System.out.println(obj + " -> " + result);
        }
    }
}