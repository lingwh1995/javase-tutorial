package org.bluebridge.lock_11_synchronized_lock_upgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * 无锁 001 无锁就是没有对任何资源进行锁定，所有线程都能访问并修改资源
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sychronized_01_LockUpgradeNoLockTest {

    public static void main(String[] args) {
        // 创建新对象，初始为无锁状态
        Object lock = new Object();

        System.out.println("无锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
    }
}
