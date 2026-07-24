package org.bluebridge.section_09_interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 获取线程打断状态方式二
 *
 * 使用try...catch...捕获异常，如果捕获到了异常，则说明打断状态发生了改变(线程被打断了)
 *
 * @author lingwh
 * @date 2026/7/13 10:30
 */
public class ThreadInterruptTest3 {

    public static void main(String[] args) throws InterruptedException {
        // 测试打断sleep状态(阻塞状态)的线程
        // testInterruptSleepThread();

        // 测试打断wait状态(阻塞状态)的线程
        testInterruptWaitThread();
    }

    /**
     * 测试打断sleep状态(阻塞状态)的线程
     *
     * @throws InterruptedException
     */
    private static void testInterruptSleepThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("已经收到线程打断信号.......");
                    // 收到打断信号后，使用 break; 直接中断当前线程运行
                    break;
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
            }
        }, "t1");
        t.start();

        System.out.println("打断标记：" + t.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(1000);
        t.interrupt();
        System.out.println("打断标记：" + t.isInterrupted());
        System.out.println("Thread " + t.getName() + " 存活状态： " + t.isAlive());
    }

    /**
     * 测试打断wait状态(阻塞状态)的线程
     *
     * @throws InterruptedException
     */
    private static Object lock = new Object();

    private static void testInterruptWaitThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait(2000);
                    } catch (InterruptedException e) {
                        System.out.println("已经收到线程打断信号.......");
                        // 收到打断信号后，使用 break; 直接中断当前线程运行
                        break;
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                }
            }
        }, "t1");
        t.start();

        System.out.println("打断标记：" + t.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(1000);
        t.interrupt();
        System.out.println("打断标记：" + t.isInterrupted());
        System.out.println("Thread " + t.getName() + " 存活状态：" + t.isAlive());
    }
}
