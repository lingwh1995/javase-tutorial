package org.bluebridge.cas_01_helloworld;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingwh
 * @desc AtomicInteger的CAS测试
 * @date 2026/7/9 00:00
 */
public class AtomicIntegerCASTest {

    public static void main(String[] args) {
        int prev = counter.get();
        int next = prev + 1;
        System.out.println(counter.compareAndSet(prev, next));
        System.out.println("counter.get() = " + counter.get());
    }

    private static AtomicInteger counter = new AtomicInteger(0);
}
