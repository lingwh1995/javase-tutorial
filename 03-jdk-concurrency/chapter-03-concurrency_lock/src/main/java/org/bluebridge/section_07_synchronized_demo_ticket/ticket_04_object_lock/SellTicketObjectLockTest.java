package org.bluebridge.section_07_synchronized_demo_ticket.ticket_04_object_lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 使用对象锁解决卖票数量大于500的情况
 *
 * @author lingwh
 * @date 2026/4/21 12:45
 */
public class SellTicketObjectLockTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int index = 1;
            private int max = 500;
            private Object lock = new Object();

            @Override
            public void run() {
                synchronized (lock) {
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
                        System.out.println("初始：" + ClassLayout.parseInstance(lock).toPrintableSimple());
                    }
                }
            }
        };
        new Thread(runnable, "窗口1").start();
        new Thread(runnable, "窗口2").start();
        new Thread(runnable, "窗口3").start();
    }
}
