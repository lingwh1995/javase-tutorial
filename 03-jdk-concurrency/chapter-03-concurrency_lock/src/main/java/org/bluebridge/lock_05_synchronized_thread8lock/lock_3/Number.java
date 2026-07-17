package org.bluebridge.lock_05_synchronized_thread8lock.lock_3;

import java.util.concurrent.TimeUnit;

/**
 * 情况3：3 1s 12 或 23 1s 1 或 32 1s 1
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Number {

    public synchronized void a() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("1......");
    }

    public synchronized void b() {
        System.out.println("2......");
    }

    public void c() {
        System.out.println("3......");
    }

    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
        new Thread(() -> {
            n1.c();
        }).start();
    }
}
