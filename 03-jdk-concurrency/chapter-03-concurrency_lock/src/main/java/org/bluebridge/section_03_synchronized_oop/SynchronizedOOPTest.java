package org.bluebridge.section_03_synchronized_oop;

/**
 * 使用面向对象方式改造 synchronized 避免临界区中发生竞态条件
 *
 * @author lingwh
 * @date 2026/4/21 09:30
 */
public class SynchronizedOOPTest {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("counter = " + room.getCounter());
    }
}
