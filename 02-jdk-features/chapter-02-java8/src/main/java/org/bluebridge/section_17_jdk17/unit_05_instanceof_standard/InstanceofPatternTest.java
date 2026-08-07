package org.bluebridge.section_17_jdk17.unit_05_instanceof_standard;

import org.junit.Test;

/**
 * JDK 17 instanceof 模式匹配测试(STANDARD 正式特性)
 *
 * instanceof 模式匹配(JEP 394) 在 JDK 16 中转正为 STANDARD 正式特性, JDK 17 中作为正式特性继续使用。
 * 它允许在 instanceof 判断时直接声明类型模式变量, 省去后续的强制类型转换, 提升代码可读性:
 *
 * 传统写法: if (obj instanceof String) { String s = (String) obj; ... }
 * 模式匹配: if (obj instanceof String s) { ... }  // 判断通过后 s 直接可用
 *
 * 模式变量作用域规则:
 * 1. 模式变量在 &amp;&amp; 的右侧可见, 可以继续参与条件判断
 * 2. 模式变量在 || 的右侧不可见(编译错误), 因为 || 右侧在 instanceof 判断为 false 时也会执行
 * 3. 模式变量在 if 语句块、! 表达式之后的 else 分支中可见
 *
 * 演化历程: instanceof 模式匹配 JDK 14(1st PREVIEW) → JDK 15(2nd) → JDK 16(JEP 394, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 18:46
 */
public class InstanceofPatternTest {

    /**
     * 测试 instanceof 模式匹配基本用法(STANDARD)
     * if (obj instanceof String s) 模式: 判断通过后模式变量 s 直接作为 String 使用, 无需强制类型转换
     */
    @Test
    public void testInstanceofPatternMatching() {
        Object obj = "Hello JDK17";
        // 真实模式匹配语法: 判断通过后模式变量 s 直接可用
        if (obj instanceof String s) {
            System.out.println("模式匹配: 字符串长度为 " + s.length());
        }
        System.out.println("--------------------------------------");
        // 传统写法对比: instanceof + 强制类型转换
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("传统写法: 字符串长度为 " + str.length());
        }
    }

    /**
     * 测试 instanceof 模式匹配结合 &amp;&amp; 运算符(STANDARD)
     * 模式变量在 &amp;&amp; 的右侧可见, 可以直接继续参与条件判断
     */
    @Test
    public void testInstanceofPatternMatchingWithAnd() {
        Object obj = "Hello";
        // 模式变量 s 在 && 右侧可见, 直接参与后续条件判断
        if (obj instanceof String s && s.length() > 3) {
            System.out.println("模式匹配 + &&: 长度大于 3 的字符串 = " + s.toUpperCase());
        }
        System.out.println("--------------------------------------");
        // 多个模式匹配结合 && 运算符
        Object number = 100;
        if (number instanceof Integer i && i > 50) {
            System.out.println("模式匹配 + &&: 大于 50 的整数 = " + i);
        }
        System.out.println("--------------------------------------");
        // 守卫条件不满足时不会进入分支
        Object smallNumber = 10;
        if (smallNumber instanceof Integer i && i > 50) {
            System.out.println("不会输出: 10 不大于 50");
        } else {
            System.out.println("模式匹配 + &&: " + smallNumber + " 不大于 50, 不满足守卫条件");
        }
    }

    /**
     * 测试 instanceof 模式匹配在 else 分支中的应用(STANDARD)
     * 模式变量在 ! 表达式取反后的 else 分支中可见
     */
    @Test
    public void testInstanceofPatternMatchingWithElse() {
        Object obj = 12345;
        // !(obj instanceof String s) 取反后, 模式变量 s 在 else 分支中可见
        if (!(obj instanceof String s)) {
            System.out.println("obj 不是字符串, 类型为: " + obj.getClass().getSimpleName());
        } else {
            System.out.println("obj 是字符串, 长度为 " + s.length());
        }
    }
}
