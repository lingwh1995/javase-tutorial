package org.bluebridge.lock_05_synchronized_thread8lock.lock_6;

import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 情况6：1s 后12， 或 2 1s后 1
 * @date 2026/7/9 00:00
 */
public class Number {
    public static synchronized void a() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("1......");
    }

    public static synchronized void b() {
        System.out.println("2......");
    }

    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(()->{
            n1.a();
        }).start();
        new Thread(()->{
            n1.b();
        }).start();
    }
}
