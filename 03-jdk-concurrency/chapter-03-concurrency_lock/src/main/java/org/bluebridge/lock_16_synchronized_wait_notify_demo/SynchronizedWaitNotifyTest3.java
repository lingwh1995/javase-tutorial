package org.bluebridge.lock_16_synchronized_wait_notify_demo;

import java.util.concurrent.TimeUnit;

/**
 * 虚假唤醒问题测试
 *
 * notify 只能随机唤醒一个 WaitSet 中的线程，这时如果有其它线程也在等待，那么就可能唤醒不了正确的线 程，称之为【虚假唤醒】
 * 解决方法，改为 notifyAll
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class SynchronizedWaitNotifyTest3 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                System.out.println("有烟没......" + hasCigarette);
                if (!hasCigarette) {
                    System.out.println("没烟，先歇会......");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("有烟没......" + hasCigarette);
                if (hasCigarette) {
                    System.out.println("可以开始干活了......");
                } else {
                    System.out.println("没干成活......");
                }
            }
        }, "小南").start();

        new Thread(() -> {
            synchronized (room) {
                Thread thread = Thread.currentThread();
                System.out.println("外卖送到没？......" + hasTakeout);
                if (!hasTakeout) {
                    System.out.println("没外卖，先歇会......");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("外卖送到没？......" + hasTakeout);
                if (hasTakeout) {
                    System.out.println("可以开始干活了......");
                } else {
                    System.out.println("没干成活......");
                }
            }
        }, "小女").start();

        TimeUnit.MILLISECONDS.sleep(1000);

        new Thread(() -> {
            synchronized (room) {
                hasTakeout = true;
                System.out.println("外卖到了噢......");
                room.notify();
            }
        }, "送外卖的").start();
    }
}
