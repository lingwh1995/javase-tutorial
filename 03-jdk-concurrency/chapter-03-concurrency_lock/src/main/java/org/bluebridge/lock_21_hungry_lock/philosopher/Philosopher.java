package org.bluebridge.lock_21_hungry_lock.philosopher;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author lingwh
 * @desc 哲学家类
 * @date 2026/7/9 00:00
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
        System.out.println("Thread " + Thread.currentThread().getName() + " eating...... " + LocalDateTime.now());
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
