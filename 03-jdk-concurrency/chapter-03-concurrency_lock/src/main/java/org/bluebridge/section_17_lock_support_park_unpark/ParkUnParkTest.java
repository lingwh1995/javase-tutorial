package org.bluebridge.section_17_lock_support_park_unpark;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport的park与unpark测试
 *
 * 1. 暂停线程运行 LockSupport.park;
 * 2. 恢复线程运行 LockSupport.unpark(thread);
 * 3. wait/notify 和 park/unpark的区别
 *    - wait/notify是Object的方法，而park/unpark是LockSupport的方法
 *    - wait/notify必须配合 Object Monitor一起使用，所以要在synchronized中使用，而park/unpark不需要
 *    - park/unpark是以线程为单位来[阻塞]和[唤醒]线程的，而notify只能随机唤醒一个等待线程，notifyAll是唤醒所有等待线程，不那么精确
 *    - park/&unpark 可以先调用unpark，而wait/notify不能先notify.
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
