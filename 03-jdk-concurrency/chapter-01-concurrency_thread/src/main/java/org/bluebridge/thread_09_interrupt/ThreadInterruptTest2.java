package org.bluebridge.thread_09_interrupt;

import java.util.concurrent.TimeUnit;

/**
 *
 * 获取线程打断状态方式一
 *     boolean isInterrupted()
 *
 * 线程打断
 * 1. interrupt()
 *    打断sleep()，wait()，join() 的线程，这几个方法都会让线程进入阻塞状态
 * 2. interrupt()和stop()区别
 *    interrupt() ，调用 interrupt()的线程会继续运行下去，但是会设置一个中断标志 true，由调用者线程决定什么时候来来判执行线程中断。
 *    stop() 虽然可以强行终止一个线程的运行，但使用stop()杀死线程，如果线程锁住了共享资源，那么当它被杀死后就再也没有机会释放锁，其它线程将永远无法获取锁，这个后果就很严重了。
 * 3. interrupt()的使用场景‌
 *    假设你在编写一个长时间运行的任务，你希望在特定条件下中断该任务。你可以在任务的关键部分检查中断标志，如果检测到中断，则优雅地停止任务并释放资源。
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class ThreadInterruptTest2 {

    public static void main(String[] args) throws InterruptedException {
        // 打断正常运行的线程
        // testInterruptNormalThread();

        // 打断sleep状态(阻塞状态)的线程
        // testInterruptSleepThread();

        // 打断wait状态(阻塞状态)的线程
        testInterruptWaitThread();
    }

    /**
     * 打断正常运行的线程
     *
     * @throws InterruptedException
     */
    public static void testInterruptNormalThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " is interrupted, stop running...");
                    break;
                }else {
                    System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                }
            }
        },"t1");
        t.start();

        System.out.println("打断标记：" + t.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(1000);
        // 打断正常运行的线程
        t.interrupt();
        System.out.println("打断标记：" + t.isInterrupted());
    }

    /**
     * 打断sleep状态(阻塞状态)的线程
     *
     * @throws InterruptedException
     */
    public static void testInterruptSleepThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("sleep执行完成(打印此代码说明没有执行打断操作).....");
            } catch (InterruptedException e) {
                //收到打断信号后，使用 抛出异常方式 直接中断当前线程运行
                throw new RuntimeException(e);
            }
        });
        t.start();

        System.out.println("打断标记：" + t.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(1000);
        // 打断正在睡眠的线程
        t.interrupt();
        System.out.println("打断标记：" + t.isInterrupted());
    }

    /**
     * 打断wait状态(阻塞状态)的线程
     *
     * @throws InterruptedException
     */
    private static Object lock = new Object();

    public static void testInterruptWaitThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(2000);
                    System.out.println("wait执行完成(打印此代码说明没有执行打断操作).....");
                } catch (InterruptedException e) {
                    //收到打断信号后，使用 抛出异常方式 直接中断当前线程运行
                    throw new RuntimeException(e);
                }
            }
        }, "t1");
        t.start();

        System.out.println("打断标记：" + t.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(1000);
        // 打断wait状态的线程
        t.interrupt();
        System.out.println("打断标记：" + t.isInterrupted());
    }
}
