package org.bluebridge.section_01_fixed_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小的线程池FixedThreadPool
 *
 * 1. 线程池大小固定，不会随着任务的增长而增长。
 * 2. 核心线程数和最大线程数相等，使用无界队列
 * 3. 使用的工作队列是LinkedBlockingQueue
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FixedThreadPool01HelloWorldTest {

    public static void main(String[] args) {
        // 创建一个固定大小为4的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 提交任务
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - 1");
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - 2");
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - 3");
        });

        // 关闭线程池（必须显式关闭）
        executor.shutdown();
    }
}
