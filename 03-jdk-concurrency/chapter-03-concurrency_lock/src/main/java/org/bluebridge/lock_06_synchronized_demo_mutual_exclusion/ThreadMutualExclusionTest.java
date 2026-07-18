package org.bluebridge.lock_06_synchronized_demo_mutual_exclusion;

import java.util.concurrent.TimeUnit;

/**
 * 演示多线程竞争同一个锁时产生的互斥现象
 *
 * 多个线程竞争同一把锁时，如果一个线程获取到了锁，其他线程只能等待锁被释放，才能获取到锁
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadMutualExclusionTest {

    private static Object lock = new Object();

    public static void main(String[] args) {
        // 线程一
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread " + Thread.currentThread().getName() + " has get lock......");
                // 模拟获取锁之后阻塞5秒......
                try {
                    // sleep()会占有锁对象
                    TimeUnit.MILLISECONDS.sleep(5000);
                    // wait()会释放锁对象
                    // lock.wait(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1").start();

        // 线程二
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread " + Thread.currentThread().getName() + " has get lock......");
            }
        }, "t2").start();
    }
}
