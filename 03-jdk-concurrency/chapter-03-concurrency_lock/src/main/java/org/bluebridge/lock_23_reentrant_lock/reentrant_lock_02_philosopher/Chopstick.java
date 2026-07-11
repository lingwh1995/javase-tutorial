package org.bluebridge.lock_23_reentrant_lock.reentrant_lock_02_philosopher;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lingwh
 * @desc 筷子类
 * @date 2026/7/9 00:00
 */
public class Chopstick extends ReentrantLock {

    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}
