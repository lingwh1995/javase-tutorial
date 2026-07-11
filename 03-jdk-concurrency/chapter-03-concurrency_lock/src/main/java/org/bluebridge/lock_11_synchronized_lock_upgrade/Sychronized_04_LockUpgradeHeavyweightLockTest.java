package org.bluebridge.lock_11_synchronized_lock_upgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * 重量级锁升级测试
 *
 * 1. 重量级锁状态   010
 *    两个或者以上线程并发的在一个对象上进行同步时，为了避免无用自旋锁cpu，轻量级锁就会升级成重量级锁，重量级锁由操作系统实现。
 *    如果在尝试加轻量级锁的过程中，CAS 操作无法成功，这时一种情况就是有其它线程为此对象加上了轻量级锁（有 竞争），这时需要进行锁膨胀，将轻量级锁变为重量级锁。
 * 2. 自旋优化
 *    重量级锁竞争的时候，还可以使用自旋来进行优化，如果当前线程自旋成功（即这时候持锁线程已经退出了同步 块，释放了锁），这时当前线程就可以避免阻塞。
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Sychronized_04_LockUpgradeHeavyweightLockTest {

    // 定义一个锁对象
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始：" + ClassLayout.parseInstance(lock).toPrintableSimple());

        // 创建第一个线程
        Thread t1 = new Thread(() -> {
            // 第一个线程尝试获取锁
            synchronized (lock) {
                System.out.println("线程 " + Thread.currentThread().getId() + " 已经获取到了重量锁......");
                System.out.println("轻量锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
                try {
                    // 模拟长时间运行，以便让其他线程有机会尝试获取锁
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建第二个线程
        Thread t2 = new Thread(() -> {
            // 第二个线程尝试获取锁
            synchronized (lock) {
                System.out.println("线程 " + Thread.currentThread().getId() + " 已经获取到了重量锁......");
                System.out.println("重量锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
            }
        });

        // 启动第一个线程
        t1.start();
        // 启动第二个线程
        t2.start();

        // 等待两个线程完成
        t1.join();
        t2.join();

        // 由于轻量锁升级为重量锁之后，即使重量锁解锁后，依旧是重量级锁,因为升级为重量级锁之后不会再降级了
        System.out.println("解锁：" + ClassLayout.parseInstance(lock).toPrintableSimple());
        System.out.println("所有线程已执行完毕......");
    }
}
