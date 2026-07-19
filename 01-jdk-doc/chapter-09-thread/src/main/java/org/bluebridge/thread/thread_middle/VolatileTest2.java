package org.bluebridge.thread.thread_middle;

/**
 * volatile 关键字测试2
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class VolatileTest2 {

    private volatile static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->:" + (++INIT_VALUE));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T2->:" + (++INIT_VALUE));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-2").start();
    }
}
