package org.bluebridge.thread_02_create.way_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lingwh
 * @desc 使用线程池创建线程
 * @date 2026/7/9 00:00
 */
public class ThreadCreateTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyThread();
            executor.execute(worker);
        }

        executor.shutdown();
//        while (!executor.isTerminated()) {
//            // 等待所有线程执行完毕
//        }

        System.out.println("All threads are finished...");
    }
}
