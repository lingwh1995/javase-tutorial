package org.bluebridge.section_04_happens_before;

import java.util.concurrent.TimeUnit;

/**
 * happens-before 线程中断可见性测试
 *
 * 线程 t1 打断 t2（interrupt）前对变量的写，对于其他线程得知 t2 被打断后对变量的读可见（通过 t2.interrupted 或 t2.isInterrupted）（线程中断机制）
 *
 * @author lingwh
 * @date 2026/4/21 18:45
 */
public class HappensBeforeTest5 {

    private static int x;

    public static void main(String[] args) {
        Thread t2 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(x);
                    break;
                }
            }
        }, "t2");
        t2.start();

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x = 10;
            t2.interrupt();
        }, "t1").start();

        while (!t2.isInterrupted()) {
            Thread.yield();
        }
        System.out.println(x);
    }
}
