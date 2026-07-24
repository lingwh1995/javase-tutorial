package org.bluebridge.section_07_synchronized_demo_ticket.ticket_05_reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用可重入锁解决卖票数量大于500的情况
 *
 * @author lingwh
 * @date 2026/4/21 13:00
 */
public class SellTicketReentrantLockTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int index = 1;
            private int max = 500;
            private Lock reentrantLock = new ReentrantLock();

            @Override
            public void run() {
                // 加锁
                reentrantLock.lock();
                while (true) {
                    if (index > max) {
                        break;
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + index++ + "张电影票......");
                }
                // 解锁
                reentrantLock.unlock();
            }
        };
        new Thread(runnable, "窗口1").start();
        new Thread(runnable, "窗口2").start();
        new Thread(runnable, "窗口3").start();
    }
}
