package org.bluebridge.section_17_lock_support_park_unpark;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 的 park 与 unpark 测试
 *
 * 1. 暂停线程运行 LockSupport.park;
 * 2. 恢复线程运行 LockSupport.unpark(thread);
 * 3. wait/notify 和 park/unpark 的区别
 *    - wait/notify 是 Object 的方法，而 park/unpark 是 LockSupport 的方法
 *    - wait/notify 必须配合 Object Monitor 一起使用，所以要在 synchronized 中使用，而 park/unpark 不需要
 *    - park/unpark 是以线程为单位来[阻塞]和[唤醒]线程的，而 notify 只能随机唤醒一个等待线程，notifyAll 是唤醒所有等待线程，不那么精确
 *    - park/&unpark 可以先调用 unpark，而 wait/notify 不能先 notify.
 *
 * @author lingwh
 * @date 2026/4/21 14:30
 */
public class ParkUnParkTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " start......");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " park......");
            LockSupport.park();
            System.out.println("Thread " + Thread.currentThread().getName() + " resume......");
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " unpark......");
        LockSupport.unpark(t1);
    }
}
