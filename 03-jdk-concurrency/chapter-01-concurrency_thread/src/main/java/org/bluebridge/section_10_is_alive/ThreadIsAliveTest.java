package org.bluebridge.section_10_is_alive;

import java.util.concurrent.TimeUnit;

/**
 * isAlive(): 判断线程是否还存活
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadIsAliveTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");

        t.start();
        System.out.println("Thread t1.isAlive() : " + t.isAlive());

        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Thread t1.isAlive() : " + t.isAlive());
    }
}
