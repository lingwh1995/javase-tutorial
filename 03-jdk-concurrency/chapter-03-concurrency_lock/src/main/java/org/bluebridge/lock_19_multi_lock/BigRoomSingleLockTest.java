package org.bluebridge.lock_19_multi_lock;

/**
 * @author lingwh
 * @desc 单把锁测试
 * @date 2026/7/9 00:00
 */
public class BigRoomSingleLockTest {

    public static void main(String[] args) {
        BigRoomSingleLock bigRoomSingleLock = new BigRoomSingleLock();
        new Thread(() -> {
            bigRoomSingleLock.study();
        },"小男").start();
        new Thread(() -> {
            bigRoomSingleLock.sleep();
        },"小女").start();
    }
}
