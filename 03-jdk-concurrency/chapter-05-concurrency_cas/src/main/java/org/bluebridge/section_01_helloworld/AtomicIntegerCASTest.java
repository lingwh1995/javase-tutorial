package org.bluebridge.section_01_helloworld;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger的CAS测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
