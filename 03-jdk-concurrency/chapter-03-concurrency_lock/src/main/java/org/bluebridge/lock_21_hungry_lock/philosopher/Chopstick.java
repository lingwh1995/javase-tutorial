package org.bluebridge.lock_21_hungry_lock.philosopher;

/**
 * @author lingwh
 * @desc 筷子类
 * @date 2026/7/9 00:00
 */
public class Chopstick {

    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}
