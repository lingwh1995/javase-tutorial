package org.bluebridge.section_17_jdk17_lts.unit_02_switch_pattern;

import org.junit.Test;

/**
 * JDK 17 Switch 模式匹配测试(PREVIEW 预览特性)
 *
 * Switch 模式匹配(JEP 406) 是 JDK 17 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 * 它允许 switch 的 case 标签直接使用模式(pattern), 主要包含:
 *   1. 类型模式: case String s -> ... 匹配时自动将变量 s 绑定为目标类型, 无需强制类型转换
 *   2. 守卫模式: case Integer i &amp;&amp; i &gt; 0 -> ... 类型模式 + 守卫条件, 同时满足才匹配
 *   3. null 处理: case null -> ... 显式匹配 null, 否则 selector 为 null 时抛出 NullPointerException
 *
 * 注意: JDK 17 中守卫模式使用 &amp;&amp; 语法, 在 JDK 21 转正后改为了 when 关键字。
 * 本类使用 JDK 17 真实语法编写, 方法名统一带 _Preview 后缀, 编译命令参考:
 *   javac --enable-preview --release 17 SwitchPatternTest.java
 *   java --enable-preview SwitchPatternTest
 *
 * 演化历程: Switch 模式匹配 JDK 17(JEP 406, 1st PREVIEW) -> JDK 18(JEP 420, 2nd) -> JDK 19(JEP 427, 3rd, when) -> JDK 20(JEP 433, 4th) -> JDK 21(JEP 441, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/18 10:00
 */
public class SwitchPatternTest {

    /**
     * 测试 switch 类型模式(PREVIEW)
     * JDK 17 PREVIEW 特性，需要 --enable-preview
     * case String s -> 匹配时直接将 s 绑定为 String 类型, 不需要强制类型转换
     */
    @Test
    public void testSwitchTypePattern_Preview() {
        // JDK 17 PREVIEW 特性，需要 --enable-preview
        Object obj = "Hello JDK17";
        // switch 表达式: case 标签直接使用类型模式 String s / Integer i
        String result = switch (obj) {
            case Integer i -> "整数: " + i;
            case String s -> "字符串: " + s;
            case null -> "null";
            default -> "其他";
        };
        System.out.println("类型模式匹配结果: " + result);
        System.out.println("--------------------------------------");
        // 不同类型的 selector 得到不同分支的匹配结果
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
     * 测试 switch 守卫模式(PREVIEW)
     * JDK 17 PREVIEW 特性，需要 --enable-preview
     * case Integer i &amp;&amp; i &gt; 0 -> 类型模式满足且守卫条件成立时才匹配, 守卫模式必须写在普通类型模式之前
     * 注意: JDK 17-18 守卫模式使用 &amp;&amp; 语法, JDK 19(JEP 427) 改为 when 关键字,
     * 当前 JDK 已无法编译 &amp;&amp; 语法, 原始语法以注释形式保留, 方法体使用 when 编写等价可编译代码
     */
    @Test
    public void testSwitchGuardedPattern_Preview() {
        // JDK 17 原始守卫模式语法(使用 &amp;&amp;, 已被 JDK 19 移除, 无法编译):
        // String positiveResult = switch (positive) {
        //     case Integer i && i > 0 -> "正整数: " + i;
        //     case Integer i -> "非正整数: " + i;
        //     case null -> "null";
        //     default -> "其他: " + positive;
        // };
        // 等价写法(when 关键字, JDK 19+ 语法): 类型模式满足且守卫条件成立时才匹配
        Object positive = 100;
        String positiveResult = switch (positive) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null";
            default -> "其他: " + positive;
        };
        System.out.println("正数匹配结果: " + positiveResult);
        System.out.println("--------------------------------------");
        // 守卫条件不成立时, 落入下一个类型模式分支
        Object negative = -100;
        String negativeResult = switch (negative) {
            case Integer i when i > 0 -> "正整数: " + i;
            case Integer i -> "非正整数: " + i;
            case null -> "null";
            default -> "其他: " + negative;
        };
        System.out.println("负数匹配结果: " + negativeResult);
    }

    /**
     * 测试 switch 对 null 的处理(PREVIEW)
     * JDK 17 PREVIEW 特性，需要 --enable-preview
     * case null -> 显式匹配 null, 否则 selector 为 null 时 switch 表达式会抛出 NullPointerException
     */
    @Test
    public void testSwitchNullHandling_Preview() {
        // JDK 17 PREVIEW 特性，需要 --enable-preview
        Object obj = null;
        // case null 标签显式处理 null 值
        String result = switch (obj) {
            case null -> "匹配到 null";
            case String s -> "字符串: " + s;
            default -> "其他";
        };
        System.out.println("null 处理结果: " + result);
    }

    /**
     * 测试 switch 语句形式的类型模式(PREVIEW)
     * JDK 17 PREVIEW 特性，需要 --enable-preview
     * 模式匹配同样适用于 switch 语句(statement), 通过 -> 箭头语法绑定模式变量
     */
    @Test
    public void testSwitchStatement_Preview() {
        // JDK 17 PREVIEW 特性，需要 --enable-preview
        Object obj = 42;
        // switch 语句形式: case 类型模式 + -> 箭头语法
        switch (obj) {
            case String s -> System.out.println("字符串: " + s);
            case Integer i -> System.out.println("整数: " + i);
            case null -> System.out.println("null");
            default -> System.out.println("其他: " + obj);
        }
    }
}
