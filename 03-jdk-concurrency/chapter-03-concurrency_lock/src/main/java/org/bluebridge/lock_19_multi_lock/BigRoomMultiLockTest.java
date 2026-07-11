package org.bluebridge.lock_19_multi_lock;

/**
 * @author lingwh
 * @desc 多把锁测试
 * @date 2026/7/9 00:00
 */
public class BigRoomMultiLockTest {

    public static void main(String[] args) {
        BigRoomMultiLock bigRoomMultiLock = new BigRoomMultiLock();
        new Thread(() -> {
            bigRoomMultiLock.study();
        },"小男").start();
        new Thread(() -> {
            bigRoomMultiLock.sleep();
        },"小女").start();
    }
}
