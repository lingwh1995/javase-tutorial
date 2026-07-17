package org.bluebridge.lock_11_synchronized_lock_upgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁升级测试
 *
 * 1. 轻量级锁   000
 *    - 当两个或者以上线程交替获取锁，当没有在对象上并发的获取锁时，偏向锁升级为轻量级锁。
 *    - 使用CAS操作（比较并交换）尝试获取锁，适合少量竞争
 * 2. 使用场景
 *    如果一个对象虽然有多线程要加锁，但加锁的时间是错开的（也就是没有竞争），那么可以使用轻量级锁来优化。
 * 3. 锁升级过程
 *    - Thread t1首先尝试获取lock对象上的锁，这时锁会进入偏向锁状态。
 *    - Thread t2随后尝试获取同一把锁，这时锁会从偏向锁状态升级为轻量级锁状态。
 *    - Thread t1中的Thread.sleep(5000)语句模拟了长时间运行的情况，这使得Thread t2在Thread t1释放锁之前就有机会尝试获取锁。
 *    - 当Thread t2尝试获取锁时，由于Thread t1已经持有锁，因此Thread t2将进行自旋，等待锁的释放。
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sychronized_03_LockUpgradeLightweightLockTest {

    // 定义一个锁对象
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始：" + ClassLayout.parseInstance(lock).toPrintableSimple());

        // 主线程尝试获取锁
        synchronized (lock) {
            System.out.println("线程 " + Thread.currentThread().getId() + " 已经获取到了偏向锁......");
            System.out.println("偏量锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
        }

        // 创建第二个线程
        Thread t = new Thread(() -> {
            // 第二个线程尝试获取锁
            synchronized (lock) {
                System.out.println("线程 " + Thread.currentThread().getId() + " 已经获取到了轻量锁......");
                System.out.println("轻量锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
            }
        });

        // 启动第二个线程
        t.start();
        t.join();

        System.out.println("解锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
        System.out.println("所有线程已执行完毕......");
    }
}
