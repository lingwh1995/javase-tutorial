package org.bluebridge.stackframe;

/**
 * @author lingwh
 * @desc 多线程栈帧测试
 * @date 2026/7/9 00:00
 */
public class MultiplyThreadStackFrameTest {

    public static void main(String[] args) {
        //单独开启一个线程调用method1()
        new Thread(new Runnable() {
            @Override
            public void run() {
                method1(20);
            }
        },"t1").start();

        // 在主线程中调用method1()
        method1(10);
    }

    public static void method1(int x) {
        System.out.println(Thread.currentThread().getName());
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    public static Object method2() {
        System.out.println(Thread.currentThread().getName());
        Object n = new Object();
        return n;
    }
}
