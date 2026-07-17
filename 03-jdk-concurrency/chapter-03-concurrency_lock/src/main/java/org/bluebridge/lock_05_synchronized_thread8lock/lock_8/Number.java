package org.bluebridge.lock_05_synchronized_thread8lock.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 情况8：1s 后12， 或 2 1s后 1
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
        Number n2 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n2.b();
        }).start();
    }
}
