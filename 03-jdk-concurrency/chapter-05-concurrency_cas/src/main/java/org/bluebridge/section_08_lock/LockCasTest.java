package org.bluebridge.section_08_lock;

import static java.lang.Thread.sleep;

/**
 * CAS锁测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LockCasTest {

    public static void main(String[] args) {
        LockCas lock = new LockCas();
        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " begin......");
            lock.lock();
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " lock......");
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " begin......");
            lock.lock();
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " lock......");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
