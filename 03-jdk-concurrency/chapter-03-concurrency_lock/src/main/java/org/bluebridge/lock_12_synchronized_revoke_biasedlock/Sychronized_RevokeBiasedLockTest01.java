package org.bluebridge.lock_12_synchronized_revoke_biasedlock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 撤销偏向锁测试(hashcode)
 *
 * 1. 测试偏向锁准备工作
 *    - JDK版本设置为1.8
 *    - VM配置参数设置为 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 * 2. 撤销偏向锁方式一：调用对象的hashcode()
 *    - 偏向锁的对象MarkWord中存储的是线程id，如果调用hashcode会导致偏向锁被撤销
 *    - 轻量锁会在锁记录中记录hashcode
 *    - 重量锁会在Monitor中记录hashcode
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sychronized_RevokeBiasedLockTest01 {

    public static void main(String[] args) {
        // 创建新对象，初始为无锁状态
        Object lock = new Object();
        // 调用对象hashcode()后会禁用对象偏向锁，也称撤销对象的可偏向状态
        System.out.println("hashCode: " + Integer.toBinaryString(lock.hashCode()));
        System.out.println("初始：" + ClassLayout.parseInstance(lock).toPrintableSimple());

        // 第一个线程获取锁，将进入偏向锁状态
        synchronized (lock) {
            // 在这里，对象头中的锁标志位被设置为偏向锁模式，并记录了当前线程ID
            System.out.println("Thread " + Thread.currentThread().getName() + " 已经获取到了偏向锁......");
            System.out.println("偏向锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
        }

        // 当前线程释放锁后，对象保持偏向锁状态，直到有其他线程尝试获取锁
        System.out.println("解锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
    }
}
