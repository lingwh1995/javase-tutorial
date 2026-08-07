package org.bluebridge.section_15_jdk15.unit_08_pattern_match;

import org.junit.Test;

/**
 * JDK 15 instanceof 模式匹配第二次预览（PREVIEW 特性，JEP 375）
 * instanceof 模式匹配(JEP 305) 在 JDK 14 作为第一次预览特性引入，JDK 15 进行第二次预览（JEP 375），JDK 16 正式转正（JEP 394, STANDARD）
 * 相比 JDK 14 的变化：无重大语法变化，主要是稳定性和兼容性改进
 * 注意：该特性在 JDK 15 为预览特性，编译和运行需要 --enable-preview
 *
 * 演化历程：JDK 14(JEP 305, 1st PREVIEW) → JDK 15(JEP 375, 2nd PREVIEW) → JDK 16(JEP 394, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 18:40
 */
public class InstanceofSecondPreviewTest {

    /**
     * 测试 instanceof 模式匹配基本用法（JDK 15 PREVIEW 特性，需要 --enable-preview）
     * 匹配成功后将对象绑定到模式变量，省去传统的手动强转
     */
    @Test
    public void testInstanceofPattern_Preview() {
        Object obj = "Hello, Pattern Matching!";
        if (obj instanceof String s) {
            System.out.println(s.length());
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试模式变量在逻辑运算中的使用
     * 模式变量 s 在 && 右侧即可使用（短路保证 instanceof 匹配成功后才执行右侧）
     */
    @Test
    public void testInstanceofPatternWithLogic_Preview() {
        Object obj = "Hello Java 15";
        if (obj instanceof String s && s.length() > 5) {
            System.out.println("匹配成功，字符串长度: " + s.length());
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试模式匹配的多种类型与非匹配场景
     * 模式变量作用域：仅在 instanceof 匹配成功后的代码块内有效
     */
    @Test
    public void testInstanceofPatternTypes_Preview() {
        Object number = 42;
        if (number instanceof Integer i) {
            System.out.println("整数: " + i);
        }
        Object text = "Java";
        if (text instanceof String s) {
            System.out.println("字符串长度: " + s.length());
        } else {
            System.out.println("不是字符串");
        }
        Object other = new Object();
        if (other instanceof String s) {
            System.out.println("匹配成功");
        } else {
            System.out.println("Object 实例不是 String，进入 else 分支");
        }
        System.out.println("--- 分割线 ---");
    }
}
