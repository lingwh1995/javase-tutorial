package org.bluebridge.section_07_synchronized_demo_ticket.ticket_01_no_lock;

/**
 * 由于线程安全问题，可能会出现卖票数量大于500的情况
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SellTicketNoLockTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int index = 1;
            private int max = 500;

            @Override
            public void run() {
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
            }
        };
        new Thread(runnable, "窗口1").start();
        new Thread(runnable, "窗口2").start();
        new Thread(runnable, "窗口3").start();
    }
}
