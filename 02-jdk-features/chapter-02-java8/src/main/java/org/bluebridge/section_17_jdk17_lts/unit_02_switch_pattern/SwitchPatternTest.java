package org.bluebridge.section_17_jdk17_lts.unit_02_switch_pattern;

import org.junit.Test;

/**
 * JDK 17 Switch 模式匹配测试(PREVIEW 特性, JEP 406, 第一次预览)
 *
 * JDK 17 引入了 Switch 模式匹配作为 PREVIEW 特性, 需要 --enable-preview 参数才能编译运行。
 * 主要增强:
 * 1. 类型模式: case Integer i -> 可以直接匹配类型并绑定变量, 无需 instanceof + 转型
 * 2. 守卫模式(guarded pattern): case String s when s.length() > 5 -> 在模式匹配后附加条件判断
 * 3. 空值处理: 可以直接在 switch 中匹配 null
 * 4. 穷举性检查: 编译器检查 switch 是否覆盖了所有可能的类型
 *
 * 注意: 本文件中的 PREVIEW 特性方法均带有 _Preview 后缀以标识。
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class SwitchPatternTest {

    /**
     * 测试 Switch 模式匹配中的类型模式(JDK 17 PREVIEW 特性, 需要 --enable-preview)
     *
     * 使用 case Integer i -> 语法直接匹配类型并绑定变量, 无需 instanceof 判断、强制类型转换。
     * 箭头语法 -> 右侧为表达式或语句块, 不需要 break。
     */
    @Test
    public void testTypePattern_Preview() {
        // JDK 17 PREVIEW 特性, 需要 --enable-preview
        Object obj = 42;
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case Double d -> "浮点数: " + d;
            case null -> "空值";
            default -> "未知类型: " + obj.getClass().getSimpleName();
        };
        System.out.println("类型模式匹配结果: " + result);
    }

    /**
     * 测试 Switch 模式匹配中的守卫模式(guarded pattern)(JDK 17 PREVIEW 特性, 需要 --enable-preview)
     *
     * 守卫模式使用 when 关键字在模式匹配后附加条件判断:
     * case String s when s.length() > 5 -> 只有当 s 是字符串且长度大于 5 时才匹配。
     */
    @Test
    public void testGuardedPattern_Preview() {
        // JDK 17 PREVIEW 特性, 需要 --enable-preview
        Object obj = "Hello, JDK17";
        String result = switch (obj) {
            case String s when s.length() > 10 -> "长字符串: " + s;
            case String s when s.length() > 5 -> "中字符串: " + s;
            case String s -> "短字符串: " + s;
            case Integer i when i > 100 -> "大整数: " + i;
            case Integer i -> "小整数: " + i;
            case null -> "空值";
            default -> "其他类型";
        };
        System.out.println("守卫模式匹配结果: " + result);
    }

    /**
     * 测试 Switch 模式匹配处理多种类型(JDK 17 PREVIEW 特性, 需要 --enable-preview)
     *
     * 在一个 switch 中同时处理 String、Integer、Double 等多种类型,
     * 每个 case 使用类型模式直接绑定变量并执行对应逻辑。
     */
    @Test
    public void testMultiTypePattern_Preview() {
        // JDK 17 PREVIEW 特性, 需要 --enable-preview
        Object[] objects = { "Java", 42, 3.14, null, true };

        for (Object obj : objects) {
            String description = switch (obj) {
                case String s -> "字符串(长度=" + s.length() + "): " + s;
                case Integer i -> "整数(平方=" + (i * i) + "): " + i;
                case Double d -> "浮点数(四舍五入=" + Math.round(d) + "): " + d;
                case null -> "空值";
                default -> "其他类型: " + obj.getClass().getSimpleName();
            };
            System.out.println(description);
        }
    }

    /**
     * 测试 Switch 模式匹配的穷举性(JDK 17 PREVIEW 特性, 需要 --enable-preview)
     *
     * 当 switch 处理 sealed 类型时, 编译器可以检查是否穷举了所有子类型,
     * 如果所有分支都已覆盖, 可以省略 default 子句。
     * 此处使用通用 Object 类型, 需要 default 子句。
     */
    @Test
    public void testExhaustiveSwitch_Preview() {
        // JDK 17 PREVIEW 特性, 需要 --enable-preview
        Object obj = "Exhaustive test";

        // 使用 switch 表达式, 必须穷举所有可能, 因此需要 default 子句
        int length = switch (obj) {
            case String s -> s.length();
            case Integer i -> i.toString().length();
            case null -> 0;
            default -> -1;
        };
        System.out.println("穷举 switch 结果: 长度 = " + length);
    }

    /**
     * 测试传统 switch 与模式匹配 switch 的对比(JDK 17 PREVIEW 特性, 需要 --enable-preview)
     *
     * 传统写法需要: instanceof 判断 + 强制类型转换 + 手动 break
     * 模式匹配写法: 直接在 case 中绑定变量, 箭头语法无需 break, 代码更简洁
     */
    @Test
    public void testTraditionalVsPattern_Preview() {
        // JDK 17 PREVIEW 特性, 需要 --enable-preview
        Object obj = 256;

        // 传统 switch 写法(JDK 14 之前的写法)
        String traditionalResult;
        if (obj instanceof String) {
            String s = (String) obj;
            traditionalResult = "字符串: " + s;
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            traditionalResult = "整数: " + i;
        } else {
            traditionalResult = "其他类型";
        }
        System.out.println("传统写法结果: " + traditionalResult);

        // 模式匹配 switch 写法(JDK 17 PREVIEW)
        String patternResult = switch (obj) {
            case String s -> "字符串: " + s;
            case Integer i -> "整数: " + i;
            case null -> "空值";
            default -> "其他类型";
        };
        System.out.println("模式匹配结果: " + patternResult);
    }
}