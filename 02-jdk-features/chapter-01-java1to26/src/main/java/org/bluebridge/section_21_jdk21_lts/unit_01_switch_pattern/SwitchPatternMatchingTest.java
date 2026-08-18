package org.bluebridge.section_21_jdk21_lts.unit_01_switch_pattern;

import org.junit.Test;

/**
 * JDK 21 LTS Switch 模式匹配测试(STANDARD 正式特性)
 *
 * Switch 模式匹配(JEP 441) 在 JDK 21 LTS 中转正为 STANDARD 正式特性，
 * 不再需要 --enable-preview。相比 JDK 17 的预览版本，JDK 21 LTS 正式版
 * 使用 when 关键字作为守卫条件，并支持 record 模式作为 case 标签。
 *
 * 本类演示 JDK 21 LTS 转正后的 switch 模式匹配全部特性:
 *   1. 类型模式: case Integer i -> 匹配时自动绑定变量
 *   2. 守卫模式: case Integer i when i > 0 -> 添加守卫条件
 *   3. null 处理: case null -> 显式匹配 null
 *   4. 枚举模式匹配: 匹配枚举常量
 *   5. switch 表达式穷尽性: 所有分支必须覆盖所有可能类型
 *
 * @author lingwh
 * @date 2026/08/06 13:59
 */
public class SwitchPatternMatchingTest {

    /**
     * 嵌套 record: Point, 用于测试 switch 中的 record 模式
     */
    public record Point(int x, int y) { }

    /**
     * 枚举: Color, 用于测试 switch 枚举类型模式匹配
     */
    public enum Color { RED, GREEN, BLUE, YELLOW }

    /**
     * 测试 switch 类型模式(STANDARD)
     * case Integer i -> 匹配时直接将 i 绑定为 Integer 类型，不需要强制类型转换
     * case String s -> 匹配时直接将 s 绑定为 String 类型
     * 这是 JDK 21 LTS 正式特性，不需要 --enable-preview
     */
    @Test
    public void testTypePattern() {
        // 测试 String 类型匹配
        Object obj = "Hello JDK 21 LTS";
        // ===== 旧版实现方式(JDK 21 之前): if-else 链 + instanceof + 强制类型转换 =====
        // String result;
        // if (obj instanceof Integer) {
        //     result = "整数: " + (Integer) obj;
        // } else if (obj instanceof String) {
        //     result = "字符串: " + (String) obj;
        // } else if (obj == null) {
        //     result = "null 值";
        // } else {
        //     result = "其他类型";
        // }
        // ===== 新版实现方式(JDK 21 起): switch 类型模式, case 直接绑定类型变量 =====
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null 值";
            default -> "其他类型";
        };
        System.out.println("类型模式匹配结果(obj 为字符串): " + result);
        System.out.println("--------------------------------------");

        // 测试 Integer 类型匹配
        Object number = 2023;
        String numberResult = switch (number) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null 值";
            default -> "其他类型";
        };
        System.out.println("类型模式匹配结果(number 为整数): " + numberResult);
        System.out.println("--------------------------------------");

        // 测试 Double 类型匹配
        Object decimal = 3.14;
        String decimalResult = switch (decimal) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case Double d -> "浮点数: " + d;
            case null -> "null 值";
            default -> "其他类型";
        };
        System.out.println("类型模式匹配结果(decimal 为浮点数): " + decimalResult);
    }

    /**
     * 测试 switch 守卫模式(STANDARD)
     * JDK 21 LTS 转正后守卫模式使用 when 关键字
     * case Integer i when i > 0 -> 类型模式满足且守卫条件成立时才匹配
     * 守卫模式必须写在普通类型模式之前，否则普通模式会先匹配
     */
    @Test
    public void testGuardedPattern() {
        // 测试正数匹配
        Object positive = 100;
        // ===== 旧版实现方式(JDK 21 之前): if-else 链 + 嵌套条件判断 =====
        // String positiveResult;
        // if (positive instanceof Integer) {
        //     int i = (Integer) positive;
        //     if (i > 0) {
        //         positiveResult = "正整数: " + i;
        //     } else {
        //         positiveResult = "非正整数: " + i;
        //     }
        // } else if (positive == null) {
        //     positiveResult = "null 值";
        // } else {
        //     positiveResult = "其他类型: " + positive;
        // }
        // ===== 新版实现方式(JDK 21 起): switch 守卫模式 when =====
        String positiveResult = switch (positive) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null 值";
            default -> "其他类型: " + positive;
        };
        System.out.println("正数匹配结果: " + positiveResult);
        System.out.println("--------------------------------------");

        // 测试负数匹配
        Object negative = -50;
        String negativeResult = switch (negative) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null 值";
            default -> "其他类型: " + negative;
        };
        System.out.println("负数匹配结果: " + negativeResult);
        System.out.println("--------------------------------------");

        // 测试零值匹配
        Object zero = 0;
        String zeroResult = switch (zero) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null 值";
            default -> "其他类型: " + zero;
        };
        System.out.println("零值匹配结果: " + zeroResult);
        System.out.println("--------------------------------------");

        // 守卫模式结合 record 模式: 匹配 x > 0 的 Point
        Object point = new Point(5, -3);
        String pointResult = switch (point) {
            case Point(int x, int y) when x > 0 && y > 0 -> "第一象限: (" + x + ", " + y + ")";
            case Point(int x, int y) -> "其他象限: (" + x + ", " + y + ")";
            case null -> "null 值";
            default -> "其他类型";
        };
        System.out.println("守卫 + record 模式匹配结果: " + pointResult);
    }

    /**
     * 测试 switch 对 null 的处理(STANDARD)
     * case null -> 显式匹配 null，如果 selector 为 null 且没有 case null 分支，
     * switch 表达式会抛出 NullPointerException
     */
    @Test
    public void testNullHandling() {
        // 测试 null 匹配
        Object obj = null;
        String result = switch (obj) {
            case null -> "匹配到 null 值";
            case String s -> "字符串: " + s;
            default -> "其他类型";
        };
        System.out.println("null 处理结果: " + result);
        System.out.println("--------------------------------------");

        // 验证没有 case null 时抛出 NPE
        try {
            Object nullObj = null;
            String npeResult = switch (nullObj) {
                case String s -> "字符串: " + s;
                default -> "其他类型";
            };
            System.out.println("未抛出异常，结果: " + npeResult);
        } catch (NullPointerException e) {
            System.out.println("捕获到 NullPointerException: 没有 case null 分支时，null selector 会抛 NPE");
        }
    }

    /**
     * 测试 switch 枚举模式匹配(STANDARD)
     * 枚举类型在 switch 中可以直接匹配枚举常量
     * 匹配所有枚举常量即满足穷尽性，不需要 default 分支
     */
    @Test
    public void testEnumPattern() {
        // 测试枚举常量匹配
        Color color = Color.RED;
        String result = switch (color) {
            case RED -> "红色";
            case GREEN -> "绿色";
            case BLUE -> "蓝色";
            case YELLOW -> "黄色";
        };
        System.out.println("枚举匹配结果(RED): " + result);
        System.out.println("--------------------------------------");

        // 测试不同枚举值
        Color blue = Color.BLUE;
        String blueResult = switch (blue) {
            case RED -> "红色";
            case GREEN -> "绿色";
            case BLUE -> "蓝色";
            case YELLOW -> "黄色";
        };
        System.out.println("枚举匹配结果(BLUE): " + blueResult);
        System.out.println("--------------------------------------");

        // 枚举类型作为 Object 时的类型模式匹配
        Object obj = Color.GREEN;
        String objResult = switch (obj) {
            case Color c when c == Color.RED -> "红色(枚举类型模式)";
            case Color c when c == Color.GREEN -> "绿色(枚举类型模式)";
            case Color c -> "其他颜色: " + c;
            case null -> "null 值";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("枚举类型模式匹配结果: " + objResult);
    }

    /**
     * 测试 switch 表达式穷尽性(STANDARD)
     * switch 表达式必须穷尽所有可能的分支，编译器会强制检查
     * 对于枚举类型，匹配所有枚举常量即满足穷尽性
     * 对于 Object 类型，需要 default 分支或覆盖所有可能类型
     */
    @Test
    public void testExhaustiveness() {
        // 枚举 switch 穷尽性: 匹配所有枚举常量即满足穷尽性
        Color color = Color.YELLOW;
        String colorResult = switch (color) {
            case RED -> "红色";
            case GREEN -> "绿色";
            case BLUE -> "蓝色";
            case YELLOW -> "黄色";
        };
        System.out.println("枚举 switch 穷尽性匹配: " + colorResult);
        System.out.println("--------------------------------------");

        // sealed 类型 + switch 穷尽性
        // 使用 sealed 接口限制可能子类型，编译器可以检查穷尽性
        Shape shape = new Circle(5.0);
        String shapeResult = switch (shape) {
            case Circle c -> "圆形，半径: " + c.radius();
            case Rectangle r -> "矩形，宽: " + r.width() + ", 高: " + r.height();
            case null -> "null 值";
        };
        System.out.println("sealed 类 switch 穷尽性匹配: " + shapeResult);
        System.out.println("--------------------------------------");

        // Object 类型穷尽性: 需要 default 分支
        Object obj = 42;
        String objResult = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null 值";
            default -> "其他类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("Object switch 穷尽性(含 default): " + objResult);
    }

    /**
     * 测试 switch 语句形式的模式匹配(STANDARD)
     * 模式匹配同样适用于 switch 语句(statement)，通过 -> 箭头语法绑定模式变量
     */
    @Test
    public void testSwitchStatement() {
        // switch 语句形式
        Object obj = 42;
        switch (obj) {
            case String s -> System.out.println("字符串: " + s);
            case Integer i -> System.out.println("整数: " + i);
            case null -> System.out.println("null 值");
            default -> System.out.println("其他类型: " + obj);
        }
        System.out.println("--------------------------------------");

        // switch 语句支持多种类型
        Object[] objects = {null, "Hello", 100, 3.14};
        for (Object o : objects) {
            switch (o) {
                case null -> System.out.println("null 值");
                case String s -> System.out.println("字符串: " + s);
                case Integer i -> System.out.println("整数: " + i);
                default -> System.out.println("其他类型: " + o.getClass().getSimpleName() + ", 值: " + o);
            }
        }
    }

    /**
     * 测试 switch 类型模式 + 守卫模式 + record 模式综合使用(STANDARD)
     * 在一个 switch 中混合使用多种模式，展示完整的模式匹配能力
     */
    @Test
    public void testMixedPatterns() {
        Object[] objects = {null, "JDK 21 LTS", 42, -10, 0, new Point(3, 4), new Point(-1, 2), 3.14, true};
        for (Object obj : objects) {
            String result = switch (obj) {
                case null -> "null 值";
                case Point(int x, int y) when x > 0 && y > 0 -> "第一象限坐标: (" + x + ", " + y + ")";
                case Point(int x, int y) when x < 0 && y > 0 -> "第二象限坐标: (" + x + ", " + y + ")";
                case Point(int x, int y) -> "其他坐标: (" + x + ", " + y + ")";
                case Integer i when i > 0 -> "正整数: " + i;
                case Integer i when i == 0 -> "零";
                case Integer i -> "负整数: " + i;
                case String s -> "字符串: " + s;
                default -> "其他类型: " + obj.getClass().getSimpleName() + ", 值: " + obj;
            };
            System.out.println("综合匹配: " + obj + " -> " + result);
        }
    }

    // sealed 层级结构，用于测试 switch 穷尽性
    public sealed interface Shape permits Circle, Rectangle { }
    public record Circle(double radius) implements Shape { }
    public record Rectangle(double width, double height) implements Shape { }
}