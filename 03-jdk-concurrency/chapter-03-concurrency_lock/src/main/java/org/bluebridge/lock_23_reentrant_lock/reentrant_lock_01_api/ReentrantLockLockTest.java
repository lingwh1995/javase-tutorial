package org.bluebridge.lock_23_reentrant_lock.reentrant_lock_01_api;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lingwh
 * @desc lock()方法
 * @date 2026/7/9 00:00
 */
public class ReentrantLockLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 得到锁......");
                //死循环
                for (int i = 0; i < 3; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 释放锁......");
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 得到锁......");
            }finally {
                lock.unlock();
                System.out.println("Thread " + Thread.currentThread().getName() + " 释放锁......");
            }
        },"t2");

        // 让thread1先执行
        t1.start();
        TimeUnit.MILLISECONDS.sleep(50);

        t2.start();
        // 主线程阻塞一会让所有子线程启动
        TimeUnit.MILLISECONDS.sleep(100);

        // 中断线程2
        t2.interrupt();
    }
}
