package org.bluebridge.section_23_reentrant_lock.reentrant_lock_03_unfair_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 非公平锁测试
 *
 * @author lingwh
 * @date 2026/4/21 20:00
 */
public class ReentrantLockNonFairLockTest {

    // 非公平锁（默认）
    private static final ReentrantLock nonFairLock = new ReentrantLock(false);

    public static void main(String[] args) {
        System.out.println("--------- 非公平锁示例 ---------");
        Runnable task = () -> {
            // 每个线程尝试获取锁 2 次
            for (int i = 0; i < 2; i++) {
                nonFairLock.lock();
                try {
                    System.out.printf("%s 获取锁 | 等待队列长度: %d%n",
                            Thread.currentThread().getName(),
                            nonFairLock.getQueueLength());
                    // 模拟业务逻辑
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    nonFairLock.unlock();
                }
            }
        };

        // 启动 3 个线程竞争锁
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + (i + 1)).start();
        }
    }
}
