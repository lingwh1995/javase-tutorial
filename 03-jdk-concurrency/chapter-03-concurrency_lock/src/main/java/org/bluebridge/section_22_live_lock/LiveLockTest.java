package org.bluebridge.section_22_live_lock;

import java.util.concurrent.TimeUnit;

/**
 * 活锁测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LiveLockTest {

    static volatile int count = 10;

    public static void main(String[] args) {
        new Thread(() -> {
            // 期望减到 0 退出循环
            while (count > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count--;
                System.out.printf("count: %d\n", count);
            }
        }, "t1").start();

        new Thread(() -> {
            // 期望超过 20 退出循环
            while (count < 20) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                System.out.printf("count: %d\n", count);
            }
        }, "t2").start();
    }
}
