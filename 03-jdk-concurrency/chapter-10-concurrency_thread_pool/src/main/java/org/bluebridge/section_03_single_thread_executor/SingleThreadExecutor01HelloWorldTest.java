package org.bluebridge.section_03_single_thread_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程执行器基础功能测试 SingleThreadExecutor
 *
 * 1. 希望多个任务排队执行。线程数固定为 1，任务数多于 1 时，会放入无界队列排队。任务执行完毕，这唯一的线程 也不会被释放。
 * 2. 使用的工作队列是LinkedBlockingQueue
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SingleThreadExecutor01HelloWorldTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

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
