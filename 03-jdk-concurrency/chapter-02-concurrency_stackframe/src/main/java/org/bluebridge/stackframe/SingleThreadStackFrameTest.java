package org.bluebridge.stackframe;

/**
 * 单线程栈帧测试
 *
 * @author lingwh
 * @date 2026/4/21 10:30
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
