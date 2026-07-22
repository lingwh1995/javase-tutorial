package org.bluebridge.section_03_state;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态 五种状态 操作系统层面 // * 六种状态 JavaAPI层面，Thread.State枚举中体现
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        // NEW
        Thread t1 = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
        }, "t1");
        System.out.println("当前线程 " + t1.getName() + " 状态: " + t1.getState());

        // RUNNABLE
        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        t2.start();
        System.out.println("当前线程 " + t2.getName() + " 状态: " + t2.getState());

        // TERMINATED
        Thread t3 = new Thread(() -> {
            // System.out.println("Thread " + Thread.currentThread().getName() + " is
            // running...");
        }, "t3");
        t3.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("当前线程 " + t3.getName() + " 状态: " + t3.getState());

        // TIMED_WAITING
        Thread t4 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t4");
        t4.start();
        // 要呈现出 TIMED_WAITING 效果，必须加下面这行代码
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("当前线程 " + t4.getName() + " 状态: " + t4.getState());

        // WAITING
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t5");
        t5.start();
        // 要呈现出 WAITING 效果，必须加下面这行代码
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("当前线程 " + t5.getName() + " 状态: " + t5.getState());

        // BLOCKED
        Thread t6 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t6");
        t6.start();
        // 要呈现出 BLOCKED 效果，必须加下面这行代码
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("当前线程 " + t6.getName() + " 状态: " + t6.getState());
    }
}
