package org.bluebridge.lock_25_reentrant_read_write_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc ReentrantReadWriteLock读写锁测试
 * @date 2026/7/9 00:00
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        // testReadLockReadLock();

        testReadLockWriteLock();
    }

    /**
     * 测试 读锁-读锁 可以并发
     */
    private static void testReadLockReadLock() {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();

        new Thread(() -> {
            dataContainer.read();
        }, "t2").start();
    }

    /**
     * 测试读锁-写锁相互阻塞
     */
    private static void testReadLockWriteLock() {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            dataContainer.write();
        }, "t2").start();
    }
}
