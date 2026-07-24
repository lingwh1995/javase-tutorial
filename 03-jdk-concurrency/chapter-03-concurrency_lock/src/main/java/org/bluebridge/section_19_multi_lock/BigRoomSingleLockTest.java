package org.bluebridge.section_19_multi_lock;

/**
 * 单把锁测试
 *
 * @author lingwh
 * @date 2026/4/21 15:00
 */
public class BigRoomSingleLockTest {

    public static void main(String[] args) {
        BigRoomSingleLock bigRoomSingleLock = new BigRoomSingleLock();
        new Thread(() -> {
            bigRoomSingleLock.study();
        }, "小男").start();
        new Thread(() -> {
            bigRoomSingleLock.sleep();
        }, "小女").start();
    }
}
