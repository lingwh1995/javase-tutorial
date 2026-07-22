package org.bluebridge.section_05_synchronized_thread8lock.lock_5;

import java.util.concurrent.TimeUnit;

/**
 * 情况5：2 1s 后 1
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

    public synchronized void b() {
        System.out.println("2......");
    }

    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
    }
}
