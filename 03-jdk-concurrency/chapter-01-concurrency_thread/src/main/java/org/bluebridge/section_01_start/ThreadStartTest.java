package org.bluebridge.section_01_start;

/**
 * 一个简单的多线程案例
 *
 * @author lingwh
 * @date 2026/4/21 09:15
 */
public class ThreadStartTest {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t2").start();
    }
}
