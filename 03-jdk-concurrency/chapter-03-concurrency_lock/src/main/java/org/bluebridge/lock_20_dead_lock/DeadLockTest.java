package org.bluebridge.lock_20_dead_lock;

import java.util.concurrent.TimeUnit;

/**
 * 1. 死锁
 *    - 一个线程需要同时获取多把锁，这时就容易发生死锁
 * 2. 发生死锁的必要条件
 *    - 互斥条件
 *      在一段时间内，一种资源只能被一个进程所使用
 *    - 请求和保持条件
 *      进程已经拥有了至少一种资源，同时又去申请其他资源。因为其他资源被别的进程所使用，该进程进入阻塞状态，并且不释放自己已有的资源
 *    - 不可抢占条件
 *      进程对已获得的资源在未使用完成前不能被强占，只能在进程使用完后自己释放
 *    - 循环等待条件
 *      发生死锁时，必然存在一个进程——资源的循环链。
 * 3. 避免死锁的方法
 *    - 在线程使用锁对象时**，顺序加锁**即可避免死锁
 * 3. 活锁
 *    - 活锁出现在两个线程互相改变对方的结束条件，最后谁也无法结束。
 * 4. 避免活锁的方法
 *    - 在线程执行时，中途给予不同的间隔时间即可。
 * 5. 死锁与活锁的区别
 *    - 死锁是因为线程互相持有对象想要的锁，并且都不释放，最后线程阻塞，停止运行的现象。
 *    - 活锁是因为线程间修改了对方的结束条件，而导致代码一直在运行，却一直运行不完的现象。
 * 6. JVM工具查看死锁
 *    - jps -> 获取DeadLockTest的进程号 -> jstack 进程号
 *    - jconsole -> DeadLockTest -> 选择线程标签页
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("Thread " + Thread.currentThread().getName() + " lock A......");

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (B) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " lock B......");
                    System.out.println("Thread " + Thread.currentThread().getName() + " 操作......");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("Thread " + Thread.currentThread().getName() + " lock B......");

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (A) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " lock A......");
                    System.out.println("Thread " + Thread.currentThread().getName() + " 操作......");
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
