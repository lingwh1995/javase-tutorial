package org.bluebridge.cas_04_atomic_aba;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lingwh
 * @desc 原子引用
 * @date 2026/7/9 00:00
 */
public class AtomicReferenceABATest {

    private static AtomicReference<String> atomicReference = new AtomicReference<>("A");

    /**
     * AtomicReference无法判断是否发生过ABA问题
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start......");
        // 获取值 A
        // 这个共享变量被它线程修改过？
        String prev = atomicReference.get();
        change();
        sleep(1000);
        // 尝试改为 C
        System.out.printf("change A->C %s\n", atomicReference.compareAndSet(prev, "C"));
    }

    private static void change() {
        new Thread(() -> {
            System.out.printf("change A->B %s\n",atomicReference.compareAndSet(atomicReference.get(), "B"));
        }, "t1").start();
        sleep(500);
        new Thread(() -> {
            System.out.printf("change B->A %s\n", atomicReference.compareAndSet(atomicReference.get(), "A"));
        }, "t2").start();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
