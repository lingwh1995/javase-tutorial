package org.bluebridge.section_04_happens_before;

/**
 * happens-before volatile 规则测试
 *
 * @author lingwh
 * @date 2026/4/21 18:00
 */
public class HappensBeforeTest2 {

    private static volatile int x;

    public static void main(String[] args) {
        new Thread(() -> {
            x = 10;
        }, "t1").start();

        new Thread(() -> {
            System.out.println(x);
        }, "t2").start();
    }
}
