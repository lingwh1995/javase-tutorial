package org.bluebridge.section_14_jdk14.unit_02_null_pointer;

import org.junit.Test;

/**
 * Java14 改进的空指针异常消息测试(STANDARD 正式特性)
 *
 * JDK14(JEP 358) 改进了 NullPointerException 的消息, 精确定位导致异常的空引用,
 * 帮助消息格式示例:
 * 1. "Cannot invoke "String.length()" because "str" is null" - 调用空引用的方法
 * 2. "Cannot read field "x" because "point" is null" - 读取空引用的字段
 * 3. "Cannot invoke "B.getC()" because the return value of "A.getB()" is null" - 链式调用中某一步返回 null
 *
 * 说明:
 * 1. 该帮助消息默认开启, 由 -XX:+ShowCodeDetailsInExceptionMessages 控制(JDK14 起默认开启)
 * 2. 可以通过 -XX:-ShowCodeDetailsInExceptionMessages 关闭, 关闭后异常消息仅显示 null
 * 3. 注意: JIT 编译过的方法可能不显示帮助消息, 这是该特性已知的限制
 *
 * 演化历程: NPE 精确消息 JDK 14 STANDARD（JEP 358）
 *
 * @author lingwh
 * @date 2026/08/05 18:31
 */
public class NPEMessageTest {

    /**
     * 测试调用空引用的方法时触发的 NPE 帮助消息
     * JDK14 起默认输出帮助消息, 精确指明是哪个引用为 null 导致异常
     */
    @Test
    public void testNpeHelpfulMessage() {
        String str = null;
        try {
            // 触发 NPE: 调用空引用的 length() 方法
            int length = str.length();
            System.out.println("字符串长度: " + length);
        } catch (NullPointerException e) {
            // JDK14 起默认输出的帮助消息示例:
            // Cannot invoke "String.length()" because "str" is null
            System.out.println("异常类型: " + e.getClass().getName());
            System.out.println("异常消息: " + e.getMessage());
        }
    }

    /**
     * 测试读取空引用字段时触发的 NPE 帮助消息
     * 帮助消息示例: Cannot read field "x" because "point" is null
     */
    @Test
    public void testNpeHelpfulMessageWithField() {
        Point point = null;
        try {
            // 触发 NPE: 读取空引用的字段
            int x = point.x;
            System.out.println("x = " + x);
        } catch (NullPointerException e) {
            System.out.println("异常类型: " + e.getClass().getName());
            System.out.println("异常消息: " + e.getMessage());
        }
    }

    /**
     * 测试链式调用时触发的 NPE 帮助消息
     * 帮助消息可以精确到链式调用中是哪一步返回了 null
     */
    @Test
    public void testNpeHelpfulMessageWithChain() {
        A a = new A();
        try {
            // 触发 NPE: a.getB() 返回 null, 继续调用 getC() 时抛出异常
            String name = a.getB().getC().getName();
            System.out.println("name = " + name);
        } catch (NullPointerException e) {
            // JDK14 起默认输出的帮助消息示例:
            // Cannot invoke "B.getC()" because the return value of "A.getB()" is null
            System.out.println("异常类型: " + e.getClass().getName());
            System.out.println("异常消息: " + e.getMessage());
        }
    }

    /**
     * 辅助类: 用于链式调用演示, getB() 返回 null 模拟链式调用中断
     */
    static class A {
        public B getB() {
            return null;
        }
    }

    /**
     * 辅助类: 用于链式调用演示
     */
    static class B {
        public C getC() {
            return new C();
        }
    }

    /**
     * 辅助类: 用于链式调用演示
     */
    static class C {
        public String getName() {
            return "hello";
        }
    }

    /**
     * 辅助类: 用于演示读取空引用的字段
     */
    static class Point {
        int x;
        int y;
    }
}
