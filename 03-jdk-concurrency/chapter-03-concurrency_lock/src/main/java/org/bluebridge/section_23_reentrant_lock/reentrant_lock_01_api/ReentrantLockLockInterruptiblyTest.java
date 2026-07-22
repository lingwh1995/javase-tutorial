package org.bluebridge.section_23_reentrant_lock.reentrant_lock_01_api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly()方法
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ReentrantLockLockInterruptiblyTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 得到锁......");
                // 死循环
                for (int i = 0; i < 3; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 释放锁......");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                // 改动点
                lock.lockInterruptibly();
                System.out.println("Thread " + Thread.currentThread().getName() + " 得到锁......");
                // 捕获中断异常
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread " + Thread.currentThread().getName() + " 中断结束......");
            } finally {
                lock.unlock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 释放锁......");
            }
        }, "t2");

        t1.start();
        // 让thread1先执行
        Thread.sleep(100);
        t2.start();

        // 主线程组合一会让子线程启动
        Thread.sleep(100);

        t2.interrupt();
    }
}
