package org.bluebridge.section_04_happens_before;

/**
 * happens-before传递性测试
 *
 * 线程 t1 打断 t2（interrupt）前对变量的写，对于其他线程得知 t2 被打断后对变量的读可见（通过 t2.interrupted 或 t2.isInterrupted）（线程中断机制）
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HappensBeforeTest6 {

    private static volatile int x;
    private static int y;

    public static void main(String[] args) {
        new Thread(() -> {
            y = 10;
            x = 20;
        }, "t1").start();

        new Thread(() -> {
            // x=20 对 t2 可见, 同时 y=10 也对 t2 可见
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        }, "t2").start();
    }
}
