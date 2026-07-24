package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal;

import java.util.Random;

/**
 * ThreadLocal 复杂测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ThreadLocalComplexTest {

    private static final ThreadLocal<String> thradlocal = new ThreadLocal<>();
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            thradlocal.set("Thread->T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + ":" + thradlocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(() -> {
            thradlocal.set("Thread->T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + ":" + thradlocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        // 串行化效果，先执行子线程，再执行主线程
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName() + ":" + thradlocal.get());
    }
}
