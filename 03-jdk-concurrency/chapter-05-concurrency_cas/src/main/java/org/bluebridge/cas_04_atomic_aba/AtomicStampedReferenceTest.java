package org.bluebridge.cas_04_atomic_aba;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 0);

    /**
     * AtomicStampedReference可以通过添加版本号来确定有没有发生过ABA问题
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start......");
        // 获取值 A
        String prev = atomicStampedReference.getReference();
        // 获取版本号
        int stamp = atomicStampedReference.getStamp();
        System.out.println("版本 : " + stamp);
        // 如果中间有其它线程干扰，发生了 ABA 现象
        change();
        sleep(1000);
        // 尝试改为 C
        System.out.println("change A->C : " + atomicStampedReference.compareAndSet(prev, "C", stamp, stamp + 1));
    }

    private static void change() {
        new Thread(() -> {
            System.out.println("change A->B : " + atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), "B",
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("更新版本为 : " + atomicStampedReference.getStamp());
        }, "t1").start();
        sleep(500);
        new Thread(() -> {
            System.out.println("change B->A : " + atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), "A",
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("更新版本为 : " + atomicStampedReference.getStamp());
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
