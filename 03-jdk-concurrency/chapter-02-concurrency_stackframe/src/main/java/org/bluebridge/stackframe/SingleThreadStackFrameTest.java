package org.bluebridge.stackframe;

/**
 * @author lingwh
 * @desc 单线程栈帧测试
 * @date 2026/7/9 00:00
 */
public class SingleThreadStackFrameTest {

    public static void main(String[] args) {
        method1(10);
    }

    public static void method1(int x) {
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    public static Object method2() {
        Object n = new Object();
        return n;
    }
}
