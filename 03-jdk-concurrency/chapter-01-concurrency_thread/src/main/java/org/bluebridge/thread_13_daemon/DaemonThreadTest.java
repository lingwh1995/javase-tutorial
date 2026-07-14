package org.bluebridge.thread_13_daemon;

/**
 * 守护线程
 *
 * 1. 什么是守护线程?
 *    有一种特殊的线程叫做守护线程，只要其它非守护线程运行结束了，即使守护线程的代码没有执行完，也会强制结束。
 * 2. 为什么主线程运行结束后，如果不将t1线程设置为守护线程，t1线程仍然会继续运行?
 *    因为Java进程启动后，内部会有多个线程同时工作，主线程只是其中的一个线程
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
        // 创建一个守护线程
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread is running...");
                try {
                    Thread.sleep(500); // 模拟任务执行
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread interrupted...");
                }
            }
        }, "t1");

        // 设置为守护线程
        daemonThread.setDaemon(true);

        // 启动守护线程
        daemonThread.start();

        // 主线程（非守护线程）执行任务
        System.out.println("Main thread starts...");
        try {
            Thread.sleep(2000); // 模拟主线程执行任务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread ends.");
    }
}
