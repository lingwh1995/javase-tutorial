package org.bluebridge;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 慢动作测试
 *
 * @author lingwh
 * @date 2026/4/21 10:15
 */
public class SlowMotionTest {

    public static void main(String[] args) {
        AtomicInteger balance = new AtomicInteger(10000);
        int mainPrev = balance.get();
        System.out.printf("try get %s\n", mainPrev);
        new Thread(() -> {
            sleep(1000);
            int prev = balance.get();
            balance.compareAndSet(prev, 9000);
            System.out.println(balance.toString());
        }, "t1").start();
        sleep(2000);
        System.out.println("try set 8000......");
        boolean isSuccess = balance.compareAndSet(mainPrev, 8000);
        System.out.printf("is success ? %s\n", isSuccess);
        if (!isSuccess) {
            mainPrev = balance.get();
            System.out.println("try set 8000......");
            isSuccess = balance.compareAndSet(mainPrev, 8000);
            System.out.printf("is success ? %s\n", isSuccess);
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
