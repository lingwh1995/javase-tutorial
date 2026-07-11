package org.bluebridge.lock_23_reentrant_lock.reentrant_lock_06_sychronized;

/**
 * @author lingwh
 * @desc Sychronized锁的可重入性
 * @date 2026/7/9 00:00
 */
public class SychronizedReentrantLock {
    public static void main(String[] args) {
        SychronizedReentrantLock sychronizedReentrantLock = new SychronizedReentrantLock();
        sychronizedReentrantLock.method1();
    }

    public synchronized void method1() {
        // 同一个线程可以再次进入 method2()
        method2();
    }

    public synchronized void method2() {
        // 执行某些操作
        System.out.println("如果控制台输出这句话说明synchronized是可重入性锁......");
    }
}
