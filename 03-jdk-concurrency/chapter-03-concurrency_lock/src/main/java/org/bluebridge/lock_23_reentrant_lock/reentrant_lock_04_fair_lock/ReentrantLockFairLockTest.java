package org.bluebridge.lock_23_reentrant_lock.reentrant_lock_04_fair_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁测试
 *
 * 1. 公平锁的锁获取流程
 *    +-------------------+
 *    |  线程1 请求锁      | ---> 进入等待队列
 *    +-------------------+
 *    +-------------------+
 *    |  线程2 请求锁      | ---> 进入等待队列
 *    +-------------------+
 *    +-------------------+
 *    |  线程3 请求锁      | ---> 进入等待队列
 *    +-------------------+
 *
 * 2. 公平锁的优点
 *    - 避免线程饥饿问题：公平锁按照请求的顺序分配锁，可以避免某些线程长时间等待，从而避免线程饥饿问题。
 *    - 任务的公平调度：在某些业务逻辑中，要求任务按照请求的顺序来处理，因此使用公平锁可以确保任务的顺序性，防止一些任务提前或被长时间延迟。
 * 3. 公平锁的缺点
 *    - 性能开销较大：公平锁需要维护一个排队机制，每当有新线程请求锁时，系统需要检查等待队列中的线程并按照顺序分配锁，这增加了锁的获取成本。
 *    - 上下文切换较频繁：由于每个线程都需要按照顺序等待获取锁，频繁的线程调度和上下文切换可能会降低系统的性能，特别是在高并发的场景中。
 *    - 降低吞吐量：由于公平锁严格按照顺序分配锁，某些本可以快速执行的线程也需要等待队列中其他线程先获取锁，这可能导致系统吞吐量的降低。
 *
 * 4. 公平锁中的上下文切换
 *    +--------------------+               +--------------------+
 *    | 线程1 获取锁         |  --> 切换到   | 线程2 等待获取锁     |
 *    +--------------------+               +--------------------+
 *    +--------------------+               +--------------------+
 *    | 线程2 获取锁         |  --> 切换到   | 线程3 等待获取锁     |
 *    +--------------------+               +--------------------+
 *
 * 5. ReentrantLock 的公平锁实现
 *      当使用公平锁时，ReentrantLock 通过内部维护一个FIFO队列来确保每个线程按照请求顺序获取锁。具体实现上，ReentrantLock
 *      使用AbstractQueuedSynchronizer（AQS）的tryAcquire方法，检查当前是否有其他线程在排队，确保锁按照顺序分配。
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ReentrantLockFairLockTest {

    // 公平锁
    private static final ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--------- 公平锁示例 ---------");
        Runnable task = () -> {
            // 每个线程尝试获取锁2次
            for (int i = 0; i < 2; i++) {
                fairLock.lock();
                try {
                    System.out.printf("%s 获取锁 | 等待队列长度: %d%n",
                            Thread.currentThread().getName(),
                            fairLock.getQueueLength());
                    // 模拟业务逻辑
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }
        };

        // 启动3个线程竞争锁
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + (i + 1)).start();
        }
    }
}
