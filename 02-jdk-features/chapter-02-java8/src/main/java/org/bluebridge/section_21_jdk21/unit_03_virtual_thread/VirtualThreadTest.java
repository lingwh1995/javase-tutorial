package org.bluebridge.section_21_jdk21.unit_03_virtual_thread;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * JDK 21 虚拟线程测试(STANDARD 正式特性)
 *
 * 虚拟线程(Virtual Threads, JEP 444) 是 JDK 21 的 STANDARD 正式特性,
 * 是一种轻量级线程, 由 JVM 管理而非操作系统, 能够显著减少编写、维护和
 * 观察高吞吐量并发应用程序的工作量。
 *
 * 虚拟线程的核心特性:
 *   1. 轻量级: 数百万虚拟线程可以在一个平台线程上运行
 *   2. 创建方式: Thread.ofVirtual(), Thread.startVirtualThread(), Executors.newVirtualThreadPerTaskExecutor()
 *   3. 阻塞不阻塞底层平台线程: 虚拟线程在阻塞操作(如 Thread.sleep)时会自动挂起
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class VirtualThreadTest {

    /**
     * 测试使用 Thread.ofVirtual().start() 创建虚拟线程(STANDARD)
     * Thread.ofVirtual() 返回一个 VirtualThreadBuilder, 用于配置和创建虚拟线程
     * start() 立即启动虚拟线程
     */
    @Test
    public void testOfVirtualStart() throws InterruptedException {
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("虚拟线程执行中...");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
            System.out.println("  线程组: " + Thread.currentThread().getThreadGroup());
        });
        // 等待虚拟线程执行完成
        vt.join();
        System.out.println("--------------------------------------");

        // 带名称的虚拟线程
        Thread namedVt = Thread.ofVirtual().name("my-virtual-thread").start(() -> {
            System.out.println("命名虚拟线程执行中...");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
        });
        namedVt.join();
    }

    /**
     * 测试使用 Thread.startVirtualThread() 创建虚拟线程(STANDARD)
     * Thread.startVirtualThread(Runnable) 是创建并启动虚拟线程的便捷方法
     * 等价于 Thread.ofVirtual().start(runnable)
     */
    @Test
    public void testStartVirtualThread() throws InterruptedException {
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
                System.out.println("任务 " + taskId + " 在虚拟线程中执行, 线程: " + Thread.currentThread().getName());
            });
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * 测试使用 Executors.newVirtualThreadPerTaskExecutor() 创建虚拟线程(STANDARD)
     * 该方法返回一个 ExecutorService, 每次 submit 任务时都会创建一个新的虚拟线程
     * 适合大量短任务的场景
     */
    @Test
    public void testVirtualThreadPerTaskExecutor() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            int taskCount = 5;
            IntStream.range(0, taskCount).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Executor 任务 " + i + " 在虚拟线程中执行, 线程: " + Thread.currentThread().getName());
                });
            });
        }
        // try-with-resources 会自动关闭 ExecutorService 并等待所有任务完成
        System.out.println("所有 Executor 任务已完成");
        System.out.println("--------------------------------------");

        // 使用 ThreadFactory 创建虚拟线程
        ThreadFactory factory = Thread.ofVirtual().name("factory-vt-", 0).factory();
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
            for (int i = 0; i < 3; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("ThreadFactory 任务 " + taskId + ", 线程: " + Thread.currentThread().getName());
                });
            }
        }
    }

    /**
     * 测试虚拟线程与平台线程的对比(STANDARD)
     * 虚拟线程运行在 carrier 线程(平台线程)上, 但虚拟线程本身是轻量级的
     * 打印线程名称和类型以区分虚拟线程和平台线程
     */
    @Test
    public void testVirtualVsPlatformThread() throws InterruptedException {
        // 平台线程
        Thread platformThread = new Thread(() -> {
            System.out.println("平台线程:");
            System.out.println("  线程名称: " + Thread.currentThread().getName());
            System.out.println("  是否是虚拟线程: " + Thread.currentThread().isVirtual());
            System.out.println("  线程 ID: " + Thread.currentThread().threadId());
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
        });
        virtualThread.join();
    }

    /**
     * 测试虚拟线程中使用 Thread.sleep(STANDARD)
     * 虚拟线程在 sleep 时不会阻塞底层平台线程, 这是虚拟线程的核心优势之一
     * 对比平台线程和虚拟线程在大量 sleep 任务时的表现
     */
    @Test
    public void testVirtualThreadSleep() throws InterruptedException {
        int taskCount = 10;
        Instant start = Instant.now();

        // 使用虚拟线程执行大量含 sleep 的任务
        Thread[] threads = new Thread[taskCount];
        for (int i = 0; i < taskCount; i++) {
            int taskId = i;
            threads[i] = Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("虚拟线程任务 " + taskId + " 完成, 线程: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        for (Thread thread : threads) {
            thread.join();
        }

        Instant end = Instant.now();
        System.out.println("虚拟线程 " + taskCount + " 个任务(每个 sleep 100ms) 总耗时: " +
                Duration.between(start, end).toMillis() + "ms");
        // 注意: 虚拟线程的 sleep 不会阻塞底层平台线程, 所以总耗时接近 100ms 而非 100*taskCount ms
        System.out.println("--------------------------------------");

        // 演示虚拟线程处理大量并发任务
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
                });
            }
        }
        end = Instant.now();
        System.out.println("虚拟线程 " + largeTaskCount + " 个并发 sleep 任务总耗时: " +
                Duration.between(start, end).toMillis() + "ms");
    }

    /**
     * 测试虚拟线程的线程名称和属性(STANDARD)
     * 虚拟线程可以设置名称、优先级等属性, 默认名称格式为 ""
     */
    @Test
    public void testVirtualThreadProperties() throws InterruptedException {
        // 默认名称的虚拟线程
        Thread defaultVt = Thread.startVirtualThread(() -> {
            System.out.println("默认虚拟线程名称: " + Thread.currentThread().getName());
        });
        defaultVt.join();
        System.out.println("--------------------------------------");

        // 自定义名称的虚拟线程
        Thread namedVt = Thread.ofVirtual().name("worker-1").start(() -> {
            System.out.println("自定义名称虚拟线程: " + Thread.currentThread().getName());
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