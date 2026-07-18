package org.bluebridge.thread_07_priority;

/**
 * 注意：yield 和设置线程优先级都不能真正的做到设置优先，这个仅仅是对操作系统的任务调度器的一个提示
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadPriorityTest {

    public static void main(String[] args) {
        // t1 线程
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Thread " + Thread.currentThread().getName() + " " + count++);
            }
        }, "t1");
        // 设置线程优先级为最小优先级
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        // t2 线程
        Thread t2 = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Thread " + Thread.currentThread().getName() + " " + count++);
            }
        }, "t2");
        // 设置线程优先级为正常优先级
        t2.setPriority(Thread.NORM_PRIORITY);
        t2.start();

        // t3 线程
        Thread t3 = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Thread " + Thread.currentThread().getName() + " " + count++);
            }
        }, "t3");
        // 设置线程优先级为正常优先级
        t3.setPriority(Thread.MAX_PRIORITY);
        t3.start();
    }
}
