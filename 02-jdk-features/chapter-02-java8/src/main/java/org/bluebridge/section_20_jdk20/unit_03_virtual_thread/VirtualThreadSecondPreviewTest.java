package org.bluebridge.section_20_jdk20.unit_03_virtual_thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * JDK 20 虚拟线程测试（PREVIEW 特性）
 * @see JEP 436: Virtual Threads (Second Preview)
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class VirtualThreadSecondPreviewTest {

    /**
     * 测试使用 Thread.ofVirtual().start() 创建虚拟线程
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testOfVirtualStart_Preview() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Thread vThread = Thread.ofVirtual()
                .name("virtual-thread-1")
                .start(() -> {
                    System.out.println("testOfVirtualStart_Preview: 虚拟线程运行中, 名称: " + Thread.currentThread().getName());
                    System.out.println("testOfVirtualStart_Preview: 是否为虚拟线程: " + Thread.currentThread().isVirtual());
                    latch.countDown();
                });
        latch.await();
        vThread.join();
    }

    /**
     * 测试使用 Thread.startVirtualThread() 创建虚拟线程
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testStartVirtualThread_Preview() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Thread vThread = Thread.startVirtualThread(() -> {
            System.out.println("testStartVirtualThread_Preview: 虚拟线程运行中");
            System.out.println("testStartVirtualThread_Preview: 当前线程: " + Thread.currentThread().getName());
            System.out.println("testStartVirtualThread_Preview: 是否是虚拟线程: " + Thread.currentThread().isVirtual());
            latch.countDown();
        });
        latch.await();
        vThread.join();
    }

    /**
     * 测试使用 Executors.newVirtualThreadPerTaskExecutor() 创建虚拟线程
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testVirtualThreadPerTaskExecutor_Preview() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 5).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("testVirtualThreadPerTaskExecutor_Preview: 任务 " + i + " 在虚拟线程中执行, 名称: "
                            + Thread.currentThread().getName() + ", 是否虚拟线程: " + Thread.currentThread().isVirtual());
                    latch.countDown();
                    return i;
                });
            });
        }
        latch.await();
    }

    /**
     * 测试虚拟线程的 ThreadFactory
     * JDK 20 PREVIEW 特性，需要 --enable-preview
     */
    @Test
    public void testVirtualThreadFactory_Preview() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ThreadFactory factory = Thread.ofVirtual().name("virtual-worker-", 0).factory();
        for (int i = 0; i < 3; i++) {
            Thread vThread = factory.newThread(() -> {
                System.out.println("testVirtualThreadFactory_Preview: " + Thread.currentThread().getName()
                        + " 运行中, 是否虚拟线程: " + Thread.currentThread().isVirtual());
                latch.countDown();
            });
            vThread.start();
        }
        latch.await();
    }
}