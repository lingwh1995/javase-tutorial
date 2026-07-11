package org.bluebridge.thread_05_yield;

/**
 * 线程礼让，yield()  提示线程调度器让出当前线程对CPU的使用
 *
 * 1. 调用yield会暂停当前正在执行的线程，并执行其他同等优先级的线程(实际上是让出了自己的CPU时间片)
 * 2. yield和设置线程优先级都不能真正的做到设置优先，这个仅仅是对操作系统的任务调度器的一个提示
 */
public class ThreadYieldTest {

    public static void main(String[] args) {
        // 线程礼让测试1
        // threadYieldTest1();

        // 线程礼让测试2
        threadYieldTest2();
    }

    /**
     * 线程礼让测试1
     */
    public static void threadYieldTest1() {
        //t1线程
        Thread t1 = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while (true) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " " + count++);
                }
            }
        }, "t1");
        t1.start();

        //t2线程
        Thread t2 = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while (true) {
                    //t2 调用线程礼让方法
                    Thread.yield();
                    System.out.println("Thread " + Thread.currentThread().getName() + " " + count++);
                }
            }
        }, "t2");
        t2.start();
    }

    /**
     * 线程礼让测试2
     */
    public static void threadYieldTest2() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is start running...");
        Thread t1 = new Thread(() -> {
            long start = System.currentTimeMillis() ;
            for (int i = 0; i < 20000000; i++) {
                i = i + 1;
                //Thread.yield();
            }
            long end = System.currentTimeMillis() ;
            System.out.println("Thread " + Thread.currentThread().getName() + " time range is " + (end - start) + " ms");
        },"t1");
        t1.start();
        System.out.println("Thread " + Thread.currentThread().getName() + " is end running...");
    }
}
