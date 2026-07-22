package org.bluebridge.section_15_synchronized_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * sleep(long n) 和 wait(long n) 的区别？
 *
 * 1. sleep是Thread方法，而wait是Object 的方法
 * 2. 不需要monitor(锁),wait()需要monitor(锁)
 * 3. sleep不需要强制和synchronized配合使用，但wait需要和synchronized 一起用
 * 4. sleep在睡眠的同时，不会释放对象锁的，但wait在等待的时候会释放对象锁,并且会把锁加入到监控队列中，等待
 *    被唤醒，如果没有没有被唤醒，就放弃了CPU执行权,进入了block()状态
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedSleepWaitTest {

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 获得锁......");
                try {
                    // TimeUnit.MILLISECONDS.sleep(20000);
                    lock.wait(20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(1000);

        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " 获得锁......");
        }
    }
}
