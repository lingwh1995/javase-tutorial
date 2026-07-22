package org.bluebridge.section_05_thread_pool_executor;

import java.util.concurrent.*;

/**
 * 适用于任务量已知，相对耗时的任务
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        int corePoolSize = 2; // 核心线程数
        int maxPoolSize = 4; // 最大线程数
        long keepAliveTime = 10; // 空闲线程存活时间（秒）
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10); // 任务队列

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                queue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        // 提交任务
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Task executed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
