package org.bluebridge.lock_23_reentrant_lock.reentrant_lock_01_api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可重入测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ReentrantLockCanReentrantTest {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        lock.lock();
        System.out.println("方法1加锁......");
        try {
            System.out.println("进入方法1......");
            method2();
        } finally {
            lock.unlock();
            System.out.println("方法1解锁......");
        }
    }

    public static void method2() {
        lock.lock();
        System.out.println("方法2加锁......");
        try {
            System.out.println("进入方法2......");
            method3();
        } finally {
            lock.unlock();
            System.out.println("方法2解锁......");
        }
    }

    public static void method3() {
        lock.lock();
        System.out.println("方法3加锁......");
        try {
            System.out.println("进入方法3......");
        } finally {
            lock.unlock();
            System.out.println("方法3解锁......");
        }
    }
}
