package org.bluebridge.section_21_jdk21_lts.unit_03_virtual_thread;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * JDK 21 LTS 虚拟线程测试(STANDARD 正式特性)
 *
 * 虚拟线程(Virtual Threads, JEP 444) 是 JDK 21 LTS 的 STANDARD 正式特性，
 * 是一种轻量级线程，由 JVM 管理而非操作系统，能够显著减少编写、维护和
 * 观察高吞吐量并发应用程序的工作量。
 *
 * 虚拟线程的核心特性:
 *   1. 轻量级: 数百万虚拟线程可以在一个平台线程上运行
 *   2. 创建方式: Thread.ofVirtual(), Thread.startVirtualThread(), Executors.newVirtualThreadPerTaskExecutor()
 *   3. 阻塞不阻塞底层平台线程: 虚拟线程在阻塞操作(如 sleep、IO)时会自动挂起
 *
 * @author lingwh
 * @date 2026/08/06 14:00
 */
public class VirtualThreadTest {

    /**
     * 测试使用 Thread.ofVirtual().start() 创建虚拟线程(STANDARD)
     * Thread.ofVirtual() 返回一个 VirtualThreadBuilder，用于配置和创建虚拟线程
     * start() 立即启动虚拟线程
     */
    @Test
    public void testOfVirtualStart() throws InterruptedException {
        // 基本虚拟线程
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("虚拟线程执行中...");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
            System.out.println("  线程优先级: " + Thread.currentThread().getPriority());
        });
        vt.join();
        System.out.println("--------------------------------------");

        // 带名称的虚拟线程
        Thread namedVt = Thread.ofVirtual().name("my-vt").start(() -> {
            System.out.println("命名虚拟线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
        });
        namedVt.join();
        System.out.println("--------------------------------------");

        // 带名称和编号的虚拟线程
        Thread numberedVt = Thread.ofVirtual().name("worker-", 1).start(() -> {
            System.out.println("编号虚拟线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
        });
        numberedVt.join();
    }

    /**
     * 测试使用 Thread.startVirtualThread() 创建虚拟线程(STANDARD)
     * Thread.startVirtualThread(Runnable) 是创建并启动虚拟线程的便捷方法
     * 等价于 Thread.ofVirtual().start(runnable)
     */
    @Test
    public void testStartVirtualThread() throws InterruptedException {
        // 单个虚拟线程
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Thread.startVirtualThread() 创建的虚拟线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
        });
        vt.join();
        System.out.println("--------------------------------------");

        // 多个虚拟线程并发执行
        int taskCount = 5;
        Thread[] threads = new Thread[taskCount];
        for (int i = 0; i < taskCount; i++) {
            int taskId = i;
            threads[i] = Thread.startVirtualThread(() -> {
                System.out.println("任务 " + taskId + " 在虚拟线程中执行，线程: " + Thread.currentThread().getName());
            });
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("--------------------------------------");

        // 使用 AtomicInteger 统计完成任务数
        AtomicInteger counter = new AtomicInteger(0);
        int totalTasks = 10;
        Thread[] tasks = new Thread[totalTasks];
        for (int i = 0; i < totalTasks; i++) {
            tasks[i] = Thread.startVirtualThread(() -> {
                counter.incrementAndGet();
            });
        }
        for (Thread task : tasks) {
            task.join();
        }
        System.out.println("已完成 " + counter.get() + " 个虚拟线程任务");
    }

    /**
     * 测试使用 Executors.newVirtualThreadPerTaskExecutor() 创建虚拟线程(STANDARD)
     * 该方法返回一个 ExecutorService，每次 submit 任务时都会创建一个新的虚拟线程
     * 适合大量短任务的场景
     */
    @Test
    public void testVirtualThreadPerTaskExecutor() {
        // 基本使用
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 5).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Executor 任务 " + i + " 在虚拟线程中执行，线程: " + Thread.currentThread().getName());
                });
            });
        }
        // try-with-resources 会自动关闭 ExecutorService 并等待所有任务完成
        System.out.println("所有 Executor 任务已完成");
        System.out.println("--------------------------------------");

        // 使用 ThreadFactory 创建虚拟线程池
        ThreadFactory factory = Thread.ofVirtual().name("pool-vt-", 0).factory();
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
            for (int i = 0; i < 3; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("ThreadFactory 任务 " + taskId + "，线程: " + Thread.currentThread().getName());
                });
            }
        }
        System.out.println("--------------------------------------");

        // 提交返回结果的 Callable 任务
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                System.out.println("Callable 任务在虚拟线程中执行");
                return "任务结果";
            });
        }
    }

    /**
     * 测试平台线程 vs 虚拟线程对比(STANDARD)
     * 对比平台线程和虚拟线程的创建方式、名称、ID 等属性
     */
    @Test
    public void testVirtualVsPlatformThread() throws InterruptedException {
        // 平台线程
        Thread platformThread = new Thread(() -> {
            System.out.println("平台线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
            System.out.println("  线程 ID: " + Thread.currentThread().threadId());
            System.out.println("  线程组: " + Thread.currentThread().getThreadGroup());
        });
        platformThread.start();
        platformThread.join();

        System.out.println("--------------------------------------");

        // 虚拟线程
        Thread virtualThread = Thread.startVirtualThread(() -> {
            System.out.println("虚拟线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
            System.out.println("  线程 ID: " + Thread.currentThread().threadId());
            System.out.println("  线程组: " + Thread.currentThread().getThreadGroup());
        });
        virtualThread.join();
    }

    /**
     * 测试虚拟线程大量并发(STANDARD)
     * 创建大量虚拟线程执行 I/O 密集型任务，验证虚拟线程的轻量级特性
     */
    @Test
    public void testLargeConcurrency() throws InterruptedException {
        // 大量虚拟线程并发执行 sleep 任务
        int taskCount = 100;
        Instant start = Instant.now();

        Thread[] threads = new Thread[taskCount];
        for (int i = 0; i < taskCount; i++) {
            int taskId = i;
            threads[i] = Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(50);
                    if (taskId % 20 == 0) {
                        System.out.println("虚拟线程任务 " + taskId + " 完成");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        for (Thread thread : threads) {
            thread.join();
        }

        Instant end = Instant.now();
        System.out.println("虚拟线程 " + taskCount + " 个并发任务(每个 sleep 50ms) 总耗时: " +
                Duration.between(start, end).toMillis() + "ms");
        System.out.println("(理论耗时接近 50ms，因为虚拟线程 sleep 不阻塞底层平台线程)");
        System.out.println("--------------------------------------");

        // 大量虚拟线程通过 ExecutorService 管理
        int largeTaskCount = 1000;
        start = Instant.now();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < largeTaskCount; i++) {
                int taskId = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (taskId % 200 == 0) {
                        System.out.println("Executor 管理的大量任务 " + taskId + " 完成");
                    }
                });
            }
        }
        end = Instant.now();
        System.out.println("Executor 管理 " + largeTaskCount + " 个虚拟线程任务总耗时: " +
                Duration.between(start, end).toMillis() + "ms");
    }

    /**
     * 测试虚拟线程的线程名称和属性(STANDARD)
     * 虚拟线程可以设置名称、优先级等属性，默认名称格式为空字符串
     */
    @Test
    public void testVirtualThreadProperties() throws InterruptedException {
        // 默认名称的虚拟线程
        Thread defaultVt = Thread.startVirtualThread(() -> {
            System.out.println("默认虚拟线程名称: '" + Thread.currentThread().getName() + "'");
            System.out.println("  线程 ID: " + Thread.currentThread().threadId());
        });
        defaultVt.join();
        System.out.println("--------------------------------------");

        // 自定义名称的虚拟线程
        Thread namedVt = Thread.ofVirtual().name("worker-1").start(() -> {
            System.out.println("自定义名称虚拟线程: " + Thread.currentThread().getName());
            System.out.println("  线程优先级: " + Thread.currentThread().getPriority());
        });
        namedVt.join();
        System.out.println("--------------------------------------");

        // 带编号的虚拟线程工厂
        ThreadFactory factory = Thread.ofVirtual().name("batch-", 0).factory();
        for (int i = 0; i < 3; i++) {
            Thread t = factory.newThread(() -> {
                System.out.println("工厂创建的虚拟线程: " + Thread.currentThread().getName());
            });
            t.start();
            t.join();
        }
    }
}