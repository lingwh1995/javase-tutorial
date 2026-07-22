package org.bluebridge.section_07_synchronized_demo_ticket.ticket_03_class_lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 使用class锁解决卖票数量大于500的情况
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SellTicketClassLockTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int index = 1;
            private int max = 500;

            @Override
            public void run() {
                synchronized (SellTicketClassLockTest.class) {
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
                        System.out.println("初始：" + ClassLayout.parseInstance(SellTicketClassLockTest.class).toPrintableSimple());
                    }
                }
            }
        };
        new Thread(runnable, "窗口1").start();
        new Thread(runnable, "窗口2").start();
        new Thread(runnable, "窗口3").start();
    }
}
