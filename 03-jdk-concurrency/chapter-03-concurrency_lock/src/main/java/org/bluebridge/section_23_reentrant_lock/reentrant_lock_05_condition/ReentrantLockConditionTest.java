package org.bluebridge.section_23_reentrant_lock.reentrant_lock_05_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的Condition条件变量测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ReentrantLockConditionTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition waitCigaretteQueue = lock.newCondition();
    private static Condition waitbreakfastQueue = lock.newCondition();
    private static volatile boolean hasCigrette = false;
    private static volatile boolean hasBreakfast = false;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lock.lock();
                while (!hasCigrette) {
                    try {
                        waitCigaretteQueue.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " 等到了它的烟......");
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                while (!hasBreakfast) {
                    try {
                        waitbreakfastQueue.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " 等到了它的早餐......");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sendBreakfast();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sendCigarette();
    }

    private static void sendCigarette() {
        lock.lock();
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " 送烟来了......");
            hasCigrette = true;
            waitCigaretteQueue.signal();
        } finally {
            lock.unlock();
        }
    }

    private static void sendBreakfast() {
        lock.lock();
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " 送早餐来了......");
            hasBreakfast = true;
            waitbreakfastQueue.signal();
        } finally {
            lock.unlock();
        }
    }
}
