package org.bluebridge.section_07_synchronized_demo_ticket.ticket_02_this_lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 使用this锁解决卖票数量大于500的情况
 *
 * @author lingwh
 * @date 2026/4/21 12:15
 */
public class SellTicketThisLockTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int index = 1;
            private int max = 500;

            /**
             * V1.0
             */
            /*
            @Override
            public synchronized void run() {
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
                    System.out.println("初始：" + ClassLayout.parseInstance(this).toPrintableSimple());
                }
            }
            */

            /**
             * V2.0
             */
            @Override
            public void run() {
                synchronized (this) {
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
                        System.out.println("初始：" + ClassLayout.parseInstance(this).toPrintableSimple());
                    }
                }
            }
        };
        new Thread(runnable, "窗口1").start();
        new Thread(runnable, "窗口2").start();
        new Thread(runnable, "窗口3").start();
    }
}
