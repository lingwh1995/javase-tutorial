package org.bluebridge.jmm_04_happens_before;

/**
 * @author lingwh
 * @desc happens-before volatile规则测试
 * @date 2026/7/9 00:00
 */
public class HappensBeforeTest2 {
    private static volatile int x;

    public static void main(String[] args) {
        new Thread(()->{
            x = 10;
        },"t1").start();

        new Thread(()->{
            System.out.println(x);
        },"t2").start();
    }
}
