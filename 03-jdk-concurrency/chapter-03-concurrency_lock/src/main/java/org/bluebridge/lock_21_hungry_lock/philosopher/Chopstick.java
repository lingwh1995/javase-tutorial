package org.bluebridge.lock_21_hungry_lock.philosopher;

/**
 * 筷子类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
