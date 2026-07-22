package org.bluebridge.section_16_synchronized_wait_notify_demo;

import java.util.concurrent.TimeUnit;

/**
 * 解决其它线程阻塞问题，但如果有其它线程也在等待条件呢？
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedWaitNotifyTest2 {

    static final Object room = new Object();
    static boolean hasCigarette = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                System.out.println("有烟没......" + hasCigarette);
                if (!hasCigarette) {
                    System.out.println("没烟，先歇会......");
                    try {
                        room.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("有烟没......" + hasCigarette);
                if (hasCigarette) {
                    System.out.println("可以开始干活了......");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    System.out.println("可以开始干活了......");
                }
            }, "其它人").start();
        }

        TimeUnit.MILLISECONDS.sleep(1000);

        new Thread(() -> {
            synchronized (room) {
                hasCigarette = true;
                System.out.println("烟到了噢......");
                room.notify();
            }
        }, "送烟的").start();
    }
}
