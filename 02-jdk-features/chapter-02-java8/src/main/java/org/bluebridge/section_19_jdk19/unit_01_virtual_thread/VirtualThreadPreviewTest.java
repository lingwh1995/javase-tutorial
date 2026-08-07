package org.bluebridge.section_19_jdk19.unit_01_virtual_thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * JDK 19 虚拟线程预览测试（JEP 425 - Virtual Threads）
 *     注意：JDK 19 PREVIEW 特性，需要 --enable-preview
 *
 * 演化历程: 虚拟线程 JDK 19(JEP 425, 1st PREVIEW) → JDK 20(JEP 436, 2nd) → JDK 21(JEP 444, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class VirtualThreadPreviewTest {

    /**
     * 使用 Thread.ofVirtual() 创建和启动虚拟线程
     */
    @Test
    public void testCreateVirtualThread_Preview() throws InterruptedException {
        // 使用 Thread.ofVirtual() 创建虚拟线程
        Thread thread = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual thread: " + Thread.currentThread());
        });
        thread.join();
    }

    /**
     * 使用 Thread.startVirtualThread() 启动虚拟线程
     */
    @Test
    public void testStartVirtualThread_Preview() throws InterruptedException {
        // 使用 Thread.startVirtualThread() 快速启动虚拟线程
        Thread thread = Thread.startVirtualThread(() -> {
            System.out.println("Start virtual thread: " + Thread.currentThread());
        });
        thread.join();
    }

    /**
     * 使用 Executors.newVirtualThreadPerTaskExecutor() 创建虚拟线程执行器
     */
    @Test
    public void testVirtualThreadPerTaskExecutor_Preview() throws InterruptedException {
        // 使用 Executors.newVirtualThreadPerTaskExecutor() 创建虚拟线程执行器
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Task " + i + " running on: " + Thread.currentThread());
                });
            });
        }
        // 等待所有虚拟线程执行完毕
        TimeUnit.MILLISECONDS.sleep(500);
    }

    /**
     * 对比平台线程和虚拟线程
     */
    @Test
    public void testComparePlatformAndVirtual_Preview() throws InterruptedException {
        // 平台线程
        Thread platformThread = new Thread(() -> {
            System.out.println("Platform thread: " + Thread.currentThread());
        });
        platformThread.start();
        platformThread.join();

        // 虚拟线程
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual thread: " + Thread.currentThread());
        });
        virtualThread.join();
    }

    /**
     * 创建大量虚拟线程演示轻量级特性
     */
    @Test
    public void testMassiveVirtualThreads_Preview() throws InterruptedException {
        // 使用虚拟线程执行器批量创建任务
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 100).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Massive task " + i + " on: " + Thread.currentThread());
                });
            });
        }
        TimeUnit.MILLISECONDS.sleep(500);
    }
}