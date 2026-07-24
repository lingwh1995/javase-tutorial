package org.bluebridge.section_03_synchronized_oop;

/**
 * 线程安全的计数房间
 *
 * @author lingwh
 * @date 2026/4/21 09:15
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
