package org.bluebridge.section_16_synchronized_wait_notify_demo;

import java.util.concurrent.TimeUnit;

/**
 * wait-notify机制演示
 *
 * 其它干活的线程，都要一直阻塞，效率太低
 * 小南线程必须睡足 2s 后才能醒来，就算烟提前送到，也无法立刻醒来加了 synchronized (room) 后，就好比小南在里面反锁了门睡觉，烟根本没法
 * 送进门，main 没加 synchronized 就好像 main 线程是翻窗户进来的解决方法，使用 wait - notify 机制
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedWaitNotifyTest1 {

    static final Object room = new Object();
    static boolean hasCigarette = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                System.out.println("有烟没......" + hasCigarette);
                if (!hasCigarette) {
                    System.out.println("没烟，先歇会......");
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
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
            // 这里能不能加 synchronized (room)？
            hasCigarette = true;
            System.out.println("烟到了噢......");
        }, "送烟的").start();
    }
}
