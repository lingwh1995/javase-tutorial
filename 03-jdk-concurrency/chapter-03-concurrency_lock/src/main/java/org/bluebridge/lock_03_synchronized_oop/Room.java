package org.bluebridge.lock_03_synchronized_oop;

/**
 * @author lingwh
 * @desc 线程安全的计数房间
 * @date 2026/7/9 00:00
 */
public class Room {
    private static int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }
}
