package org.bluebridge.thread_pool_02_cached_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lingwh
 * @desc 可缓存线程池基础功能测试
 * @date 2026/7/9 00:00
 */
public class CachedThreadPool01HelloWorldTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

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
