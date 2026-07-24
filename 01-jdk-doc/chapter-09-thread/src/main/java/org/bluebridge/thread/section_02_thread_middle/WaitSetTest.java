package org.bluebridge.thread.section_02_thread_middle;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 线程 wait set 测试
 *
 * @author lingwh
 * @date 2019/10/15 10:30
 */
public class WaitSetTest {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        /**
         * 1. 所有的对象都有一个 wait set，用来存放该对象 wait() 方法之后进入 blocked 状态的线程
         * 2. 调用 notify() 后，wait set 中线程被唤醒顺序不是 FIFO
         * 3. 线程被 notify() 之后，不一定被立即执行
         */
        new Thread(() -> {
            work();
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (LOCK) {
            LOCK.notify();
        }
        // worksetTest();
    }

    private static void work() {
        synchronized (LOCK) {
            System.out.println("Begin......");

            try {
                System.out.println("Thread will coming......");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out......");
        }
    }

    private static void worksetTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            new Thread(() -> {
                synchronized (LOCK) {
                    try {
                        Optional.of(Thread.currentThread().getName() + " will come to set ")
                                .ifPresent(System.out::println);
                        LOCK.wait();
                        Optional.of(Thread.currentThread().getName() + " will leave set ")
                                .ifPresent(System.out::println);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        IntStream.rangeClosed(1, 10).forEach(i -> {
            synchronized (LOCK) {
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
