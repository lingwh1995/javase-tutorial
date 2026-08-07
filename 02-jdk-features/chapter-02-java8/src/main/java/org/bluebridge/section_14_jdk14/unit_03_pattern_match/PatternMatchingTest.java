package org.bluebridge.section_14_jdk14.unit_03_pattern_match;

import org.junit.Test;

/**
 * Java14 instanceof 模式匹配测试(PREVIEW 特性)
 *
 * instanceof 模式匹配(JEP 305) 是 JDK14 引入的 PREVIEW 特性, 允许在 instanceof 判断时
 * 直接声明类型模式变量, 省去后续的强制类型转换, 提升代码可读性。
 *
 * 传统写法: if (obj instanceof String) { String s = (String) obj; ... }
 * 模式匹配: if (obj instanceof String s) { ... }  // 判断通过后 s 直接可用
 *
 * 模式变量作用域规则:
 * 1. 模式变量在 && 的右侧可见, 可以继续参与条件判断
 * 2. 模式变量在 || 的右侧不可见(编译错误), 因为 || 右侧在 instanceof 判断为 false 时也会执行
 * 3. 模式变量在 if 语句块、! 表达式之后的 else 分支中可见
 *
 * 注意: instanceof 模式匹配在 JDK 14 中是 PREVIEW 特性, 需要 JDK 14 + --enable-preview 才能编译,
 *       本文件使用真实的模式匹配语法编写
 *
 * 演化历程: instanceof 模式匹配 JDK 14(JEP 305, 1st PREVIEW) → JDK 15(JEP 375, 2nd PREVIEW) → JDK 16(JEP 394, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 18:31
 */
public class PatternMatchingTest {

    /**
     * 测试 instanceof 模式匹配基本用法(PREVIEW)
     * if (obj instanceof String s) 模式: 判断通过后 s 直接作为 String 使用, 无需强制类型转换
     */
    @Test
    public void testInstanceofPatternMatching_Preview() {
        Object obj = "Hello Java14";
        // 模式匹配: 判断通过后模式变量 s 直接可用, 无需强制类型转换
        if (obj instanceof String s) {
            System.out.println("模式匹配: 字符串长度为 " + s.length());
        }
    }

    /**
     * 测试 instanceof 模式匹配结合 &amp;&amp; 运算符(PREVIEW)
     * 模式变量在 &amp;&amp; 的右侧可见, 可以直接继续参与条件判断
     */
    @Test
    public void testInstanceofPatternMatchingWithAnd_Preview() {
        Object obj = "Hello";
        // 模式变量 s 在 && 右侧可见, 可以直接参与后续条件判断
        if (obj instanceof String s && s.length() > 3) {
            System.out.println("模式匹配: 长度大于 3 的字符串 = " + s.toUpperCase());
        }
    }

    /**
     * 测试 instanceof 模式匹配结合 || 运算符(PREVIEW)
     * 注意: 模式变量在 || 的右侧不可见(编译错误), 因为 || 右侧在 instanceof 判断为 false 时也会执行;
     *       || 只能出现在模式变量已确定绑定的位置(如 && 的右侧)
     */
    @Test
    public void testInstanceofPatternMatchingWithOr_Preview() {
        Object obj = "";
        // 正确写法: || 出现在模式变量已确定绑定的 && 右侧, 模式变量 s 在 && 右侧可见
        if (obj instanceof String s && (s.length() > 3 || s.isEmpty())) {
            System.out.println("模式匹配: 长度大于 3 或为空字符串");
        }
        // 注意: 错误写法 if (obj instanceof String s || s.isEmpty()) 无法编译,
        // 因为模式变量 s 在 || 的右侧不可见(作用域未覆盖到 || 右侧)
    }
}
