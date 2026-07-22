package org.bluebridge.section_08_join;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程join同步
 *
 * 1. join() 用于实现线程同步
 *    https://blog.csdn.net/csdn_life18/article/details/139520863
 *    - 同步和异步
 *      需要等待结果返回，才能继续运行就是同步
 *      不需要等待结果返回，就能继续运行就是异步
 *    - Java提供了多种机制来实现线程之间的协调，其中之一就是join()方法。join()方法允许一个线程等待另一个线程完成，这在很多场景中是非常有用的。
 *    - 在A线程中调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行
 * 2. join()的使用场景‌
 *    在多线程编程中，当你需要确保某个任务完成后再继续执行下一个任务时，可以使用join()。例如，在一个生产者-消费者模型中，消费者线程可以在生产者
 *    线程之后使用join()方法，以确保生产者线程先完成任务。通过合理使用interrupt和join方法，可以有效地管理多线程程序中的同步和异步操作，避免资
 *    源竞争和死锁等问题。
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        // 测试使用 join() 实现线程同步
        // testThreadJoin1();

        // 测试使用 join() 实现线程同步
        testThreadJoin2();

        // 测试使用 join() 实现线程同步
        // testThreadJoin3();

        // 测试使用 join() 实现线程同步
        // testThreadJoin4();

        // 测试使用 join() 实现线程同步
        // testThreadJoin5();
    }

    /**
     * 测试使用 join() 实现线程同步
     *
     * @throws InterruptedException
     */
    public static void testThreadJoin1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }, "t2");

        t1.start();
        // 同步执行线程一
        t1.join();

        t2.start();
        // 同步执行线程二
        t2.join();

        System.out.println("Thread " + Thread.currentThread().getName() + " is running...");
    }

    /**
     * 测试使用 join() 实现线程同步
     *
     * @throws InterruptedException
     */
    public static void testThreadJoin2() throws InterruptedException {
        System.out.println("Thread " + Thread.currentThread().getName() + " is start running...");
        Thread t1 = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is start sleeping...");
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " is end sleeping...");
        });
        t1.start();
        // t1.join();
        System.out.println("Thread " + Thread.currentThread().getName() + " is end running...");
    }

    /**
     * 测试使用 join() 实现线程同步
     *
     * @throws InterruptedException
     */
    public static void testThreadJoin3() throws InterruptedException {
        AtomicInteger i = new AtomicInteger(1);
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                i.set(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        // 如果不使用 join() 实现线程同步的话，获取到的i的值是 1，如果使用 join() 实现线程同步，获取到的i的值是 10
        t1.join();
        System.out.println("i = " + i.get());
    }

    /**
     * 测试使用 join() 实现线程同步 第一个 join()：等待时,t2并没有停止,而在运行 第二个
     * join()：1s后,执行到此,t2也运行了1s,因此也只需再等待 1s
     *
     * @throws InterruptedException
     */
    public static void testThreadJoin4() throws InterruptedException {
        AtomicInteger r1 = new AtomicInteger(0);
        AtomicInteger r2 = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                r1.set(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                r2.set(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("r1 = " + r1.get());
        System.out.println("r2 = " + r2.get());
        System.out.println("timeRange = " + (end - start));
    }

    /**
     * 测试使用 join(long millis) 实现线程同步 限时同步
     *
     * @throws InterruptedException
     */
    public static void testThreadJoin5() throws InterruptedException {
        AtomicInteger i = new AtomicInteger(1);
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                i.set(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        // 如果 不使用join(long millis)实现线程同步 或使用join(long millis)实现线程同步失败 的话，获取到的i的值是 1，如果使用 join()
        // 实现线程同步，获取到的i的值是 10

        /*
         * 1. 注释掉下面一行，演示 不使用join(long millis)实现线程同步
         * 2. 参数传递1000，即 t1.join(1000) 演示 使用join(long millis)实现线程同步失败
         * 3. 参数传递3000，即 t1.join(3000) 演示 使用join(long millis)实现线程同步成功
         */
        t1.join(1000);
        System.out.println("i = " + i.get());
    }
}
