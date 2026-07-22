package org.bluebridge.section_20_dead_lock.philosopher;

import java.util.concurrent.TimeUnit;

/**
 * 哲学家类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Philosopher extends Thread {

    private Chopstick left;
    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() {
        System.out.println("Thread " + Thread.currentThread().getName() + " eating......");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            // 获得左手筷子
            synchronized (left) {
                // 获得右手筷子
                synchronized (right) {
                    // 吃饭
                    eat();
                }
                // 放下右手筷子
            }
            // 放下左手筷子
        }
    }
}
