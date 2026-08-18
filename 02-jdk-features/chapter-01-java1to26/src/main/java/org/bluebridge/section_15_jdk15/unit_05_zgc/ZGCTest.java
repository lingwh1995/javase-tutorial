package org.bluebridge.section_15_jdk15.unit_05_zgc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * JDK 15 ZGC 测试（JEP 377，STANDARD 特性）
 *
 * ZGC（Z Garbage Collector）是一个可伸缩的低延迟垃圾收集器，
 * 在 JDK 11 中作为预览特性引入，在 JDK 15 中成为标准特性。
 *
 * 演化历程: ZGC JDK 11 实验性 → JDK 15(JEP 377, STANDARD)
 *
 * ZGC 主要特点：
 * 1. 暂停时间不超过 10ms
 * 2. 堆大小从几百 MB 到 TB 级别
 * 3. 吞吐量影响不超过 15%
 * 4. 支持并发处理（并发标记、并发重定位）
 * 5. 支持 NUMA 感知
 *
 * 运行说明：
 * 在 VM options 中设置 ZGC 参数：
 * -XX:+UseZGC -Xms256m -Xmx256m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xlog:gc*
 *
 * 简化版参数：
 * -XX:+UseZGC -Xms256m -Xmx256m -Xlog:gc
 *
 * @author lingwh
 * @date 2026/08/06 14:08
 */
public class ZGCTest {

    private static final int OBJECT_COUNT = 100_000;
    private static final int ALLOCATION_SIZE = 1024; // 1KB

    /**
     * 测试 ZGC 基本内存分配
     *
     * 创建大量对象并触发 GC，验证 ZGC 能够正常处理内存分配和回收。
     * 运行前请设置 VM options: -XX:+UseZGC -Xms256m -Xmx256m -Xlog:gc
     */
    @Test
    public void testZGCBasicAllocation() {
        System.out.println("=== ZGC 基本内存分配测试 ===");
        System.out.println("注意: 运行此测试需要在 VM options 中设置 ZGC 参数");
        System.out.println("例如: -XX:+UseZGC -Xms256m -Xmx256m -Xlog:gc");
        System.out.println();

        // 打印当前 GC 信息
        System.out.println("当前使用的 GC: " + getCurrentGC());
        System.out.println("最大可用内存: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB");
        System.out.println("初始可用内存: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB");

        // 分配大量对象
        System.out.println("开始分配 " + OBJECT_COUNT + " 个对象...");
        List<byte[]> objects = new ArrayList<>(OBJECT_COUNT);
        for (int i = 0; i < OBJECT_COUNT; i++) {
            objects.add(new byte[ALLOCATION_SIZE]);
        }
        System.out.println("分配完成，当前列表大小: " + objects.size());

        // 打印分配后的内存使用
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("已使用内存: " + usedMemory / (1024 * 1024) + " MB");

        // 手动触发 GC
        System.out.println("手动触发 GC...");
        System.gc();

        // 等待 GC 完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 清除引用
        objects.clear();

        // 再次触发 GC
        System.out.println("清除引用后再次触发 GC...");
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 打印 GC 后的内存使用
        long afterGCUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("GC 后已使用内存: " + afterGCUsedMemory / (1024 * 1024) + " MB");
        System.out.println("ZGC 基本内存分配测试完成");
    }

    /**
     * 测试 ZGC 多线程环境下的内存分配
     *
     * 使用多个线程同时分配对象，测试 ZGC 在并发场景下的表现。
     */
    @Test
    public void testZGCConcurrentAllocation() throws Exception {
        System.out.println("=== ZGC 并发分配测试 ===");
        System.out.println("当前使用的 GC: " + getCurrentGC());

        int threadCount = 4;
        int objectsPerThread = 50_000;
        Thread[] threads = new Thread[threadCount];

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 启动多个线程并发分配对象
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                List<byte[]> localObjects = new ArrayList<>();
                for (int j = 0; j < objectsPerThread; j++) {
                    localObjects.add(new byte[ALLOCATION_SIZE]);
                }
                System.out.println("线程 " + threadId + " 分配了 " + objectsPerThread + " 个对象");

                // 模拟对象存活一段时间
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // 清理
                localObjects.clear();
                System.out.println("线程 " + threadId + " 已清理对象");
            }, "Allocator-" + i);
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("并发分配完成，耗时: " + elapsed + "ms");
        System.out.println("总分配对象数: " + (threadCount * objectsPerThread));
    }

    /**
     * 测试 ZGC 大对象分配
     *
     * ZGC 对大对象的处理与普通对象不同，本测试验证 ZGC 能够正常处理大对象分配。
     */
    @Test
    public void testZGCLargeObjectAllocation() {
        System.out.println("=== ZGC 大对象分配测试 ===");
        System.out.println("当前使用的 GC: " + getCurrentGC());

        // 分配不同大小的对象
        System.out.println("分配 1MB 对象...");
        byte[] mb1 = new byte[1024 * 1024];

        System.out.println("分配 4MB 对象...");
        byte[] mb4 = new byte[4 * 1024 * 1024];

        System.out.println("分配 8MB 对象...");
        byte[] mb8 = new byte[8 * 1024 * 1024];

        System.out.println("大对象分配成功");

        // 清除大对象引用
        mb1 = null;
        mb4 = null;
        mb8 = null;

        System.out.println("触发 GC 回收大对象...");
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long afterGCUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("GC 后已使用内存: " + afterGCUsedMemory / (1024 * 1024) + " MB");
        System.out.println("大对象分配测试完成");
    }

    /**
     * 测试 ZGC 内存分配和释放的性能
     *
     * 通过循环分配和释放对象来测试 ZGC 的性能表现。
     */
    @Test
    public void testZGCPerformance() {
        System.out.println("=== ZGC 性能测试 ===");
        System.out.println("当前使用的 GC: " + getCurrentGC());
        System.out.println("最大可用内存: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB");

        int iterations = 10;
        long totalAllocationTime = 0;
        long totalGcTime = 0;

        for (int iter = 0; iter < iterations; iter++) {
            // 分配对象
            List<byte[]> objects = new ArrayList<>();
            long allocStart = System.nanoTime();
            for (int i = 0; i < 20_000; i++) {
                objects.add(new byte[ALLOCATION_SIZE]);
            }
            long allocEnd = System.nanoTime();
            totalAllocationTime += (allocEnd - allocStart);

            // 清理
            objects.clear();

            // 触发 GC 并计时
            long gcStart = System.nanoTime();
            System.gc();
            long gcEnd = System.nanoTime();
            totalGcTime += (gcEnd - gcStart);

            System.out.println("迭代 " + (iter + 1) + ": 分配耗时 " +
                    (allocEnd - allocStart) / 1_000_000 + "ms, GC 耗时 " +
                    (gcEnd - gcStart) / 1_000_000 + "ms");
        }

        System.out.println("平均分配耗时: " + (totalAllocationTime / iterations) / 1_000_000 + "ms");
        System.out.println("平均 GC 耗时: " + (totalGcTime / iterations) / 1_000_000 + "ms");
        System.out.println("ZGC 性能测试完成");
    }

    /**
     * 测试 ZGC 内存压力测试
     *
     * 模拟高内存压力场景，测试 ZGC 在接近堆上限时的表现。
     */
    @Test
    public void testZGCMemoryPressure() {
        System.out.println("=== ZGC 内存压力测试 ===");
        System.out.println("当前使用的 GC: " + getCurrentGC());

        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("最大堆内存: " + maxMemory / (1024 * 1024) + " MB");

        // 计算可用内存的 80% 作为压力阈值
        long pressureThreshold = (long) (maxMemory * 0.8);
        System.out.println("压力阈值 (80%): " + pressureThreshold / (1024 * 1024) + " MB");

        // 循环分配对象直到接近压力阈值
        List<byte[]> objects = new ArrayList<>();
        long totalAllocated = 0;
        int allocationCount = 0;

        try {
            while (totalAllocated < pressureThreshold) {
                // 每次分配 1MB
                byte[] chunk = new byte[1024 * 1024];
                objects.add(chunk);
                totalAllocated += 1024 * 1024;
                allocationCount++;

                if (allocationCount % 50 == 0) {
                    long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                    System.out.println("已分配: " + allocationCount + " 个对象, " +
                            "已用内存: " + usedMemory / (1024 * 1024) + " MB");
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("达到内存上限，分配了 " + allocationCount + " 个对象后 OOM");
        }

        System.out.println("内存压力测试完成，共分配 " + allocationCount + " 个 1MB 对象");

        // 清理
        objects.clear();
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long afterGCUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("清理后已使用内存: " + afterGCUsedMemory / (1024 * 1024) + " MB");
    }

    /**
     * 获取当前使用的 GC 名称
     */
    private String getCurrentGC() {
        // 通过 Runtime 参数获取 GC 信息
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments()
                .stream()
                .filter(arg -> arg.contains("Use") && arg.contains("GC"))
                .findFirst()
                .orElse("Default GC (可通过 -XX:+UseZGC 启用 ZGC)");
    }
}