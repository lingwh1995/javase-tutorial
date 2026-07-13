package org.bluebridge.lock_19_multi_lock;

import java.util.concurrent.TimeUnit;

/**
 * 多把锁
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BigRoomMultiLock {

    private final Object studyRoom = new Object();
    private final Object bedRoom = new Object();

    public void sleep() {
        synchronized (bedRoom) {
            System.out.println("Thread " + Thread.currentThread().getName() + " 睡觉两个小时......");
            try {
                TimeUnit.MILLISECONDS.sleep(1000 * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void study() {
        synchronized (studyRoom) {
            System.out.println("Thread " + Thread.currentThread().getName() + " 学习一个小时......");
            try {
                TimeUnit.MILLISECONDS.sleep(1000 * 5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
