package org.bluebridge.section_23_jdk23.unit_02_zgc;

import org.junit.Test;

import java.lang.management.ManagementFactory;

/**
 * JDK 23 ZGC 分代模式默认启用测试（STANDARD 正式特性）
 *
 * ZGC 分代模式默认启用(ZGC Generational Mode by Default, JEP 474) 是 JDK 23
 * 的 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 *
 * ZGC (Z Garbage Collector) 是 JDK 11 引入的低延迟垃圾收集器。
 * JDK 21 引入了 ZGC 分代模式(Generational ZGC), 通过维护年轻代和老年代
 * 来减少 GC 暂停时间。
 *
 * JDK 23 的变化:
 *   1. 分代模式现在是 ZGC 的默认模式(之前需要通过 -XX:+ZGenerational 启用)
 *   2. 非分代模式被标记为废弃, 通过 -XX:-ZGenerational 可以暂时使用
 *   3. 后续版本将移除非分代模式
 *   4. 分代 ZGC 在大部分场景下能显著降低 GC 开销
 *
 * 本类通过 JVM 参数说明和运行时行为演示 ZGC 分代模式。
 *
 * 演化历程: ZGC 分代模式 JDK 23 STANDARD（JEP 474）
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class ZGCTest {

    /**
     * 测试 ZGC 分代模式默认启用说明(STANDARD)
     * 从 JDK 23 开始, ZGC 默认使用分代模式
     * 运行参数: -XX:+UseZGC
     */
    @Test
    public void testZGCGenerationalDefault() {
        // JDK 23 STANDARD 正式特性
        // 从 JDK 23 开始, -XX:+UseZGC 自动启用分代模式
        System.out.println("===== ZGC 分代模式默认启用 =====");
        System.out.println("JDK 23 开始, ZGC 默认使用分代模式");
        System.out.println("--------------------------------------");
        System.out.println("启用 ZGC 的 JVM 参数:");
        System.out.println("  -XX:+UseZGC              // JDK 23 默认启用分代模式");
        System.out.println("  -XX:+UseZGC -XX:+ZGenerational  // 显式启用分代模式(JDK 21/22)");
        System.out.println("  -XX:+UseZGC -XX:-ZGenerational  // 使用非分代模式(已废弃)");
        System.out.println("--------------------------------------");
        System.out.println("分代 ZGC 的优势:");
        System.out.println("  1. 更低的 GC 暂停时间");
        System.out.println("  2. 更低的内存开销");
        System.out.println("  3. 更高的吞吐量");
        System.out.println("  4. 更少的 CPU 使用率");
    }

    /**
     * 测试 ZGC 运行时内存分配行为(STANDARD)
     * 演示 ZGC 作为低延迟垃圾收集器在内存分配中的表现
     */
    @Test
    public void testZGCAllocation() {
        // JDK 23 STANDARD 正式特性
        // 演示 ZGC 运行时的内存分配行为
        System.out.println("===== ZGC 内存分配测试 =====");

        // 获取当前 JVM 的 GC 信息
        System.out.println("JVM 信息:");
        System.out.println("  Java 版本: " + System.getProperty("java.version"));
        System.out.println("  JVM 名称: " + System.getProperty("java.vm.name"));
        System.out.println("  JVM 参数: " + ManagementFactory.getRuntimeMXBean().getInputArguments());
        System.out.println("--------------------------------------");

        // 分配大量对象, 观察 ZGC 行为
        int objectCount = 10_000;
        System.out.println("创建 " + objectCount + " 个对象, 触发 ZGC 行为...");

        // 创建大量对象, 模拟内存压力
        java.util.List<byte[]> list = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 每次分配 1MB 的数组
            byte[] data = new byte[1024 * 1024];
            list.add(data);
        }

        // 获取内存使用情况
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("内存使用情况:");
        System.out.println("  总内存: " + (totalMemory / 1024 / 1024) + " MB");
        System.out.println("  已用内存: " + (usedMemory / 1024 / 1024) + " MB");
        System.out.println("  空闲内存: " + (freeMemory / 1024 / 1024) + " MB");
        System.out.println("--------------------------------------");
        System.out.println("注意: 使用 -XX:+UseZGC 运行本测试可以观察 ZGC 行为");
        System.out.println("完整运行命令: java -XX:+UseZGC -XX:ZGenerational ZGCTest");
    }

    /**
     * 测试 ZGC 分代模式与非分代模式的对比(STANDARD)
     * 说明 JDK 23 中分代模式的优势
     */
    @Test
    public void testZGCGenerationalComparison() {
        // JDK 23 STANDARD 正式特性
        System.out.println("===== 分代 ZGC vs 非分代 ZGC 对比 =====");
        System.out.println();
        System.out.println("+------------------+------------------+------------------+");
        System.out.println("| 特性             | 分代 ZGC         | 非分代 ZGC       |");
        System.out.println("+------------------+------------------+------------------+");
        System.out.println("| 暂停时间         | 更低             | 低               |");
        System.out.println("| 堆内存开销       | 更小             | 更大             |");
        System.out.println("| CPU 使用率       | 更低             | 更高             |");
        System.out.println("| 吞吐量           | 更高             | 中等             |");
        System.out.println("| 配置复杂度       | 默认启用         | 已废弃           |");
        System.out.println("+------------------+------------------+------------------+");
        System.out.println();
        System.out.println("===== JVM 参数配置 =====");
        System.out.println("JDK 23 推荐配置:");
        System.out.println("  java -XX:+UseZGC -jar myapp.jar");
        System.out.println();
        System.out.println("JDK 21/22 需要显式启用分代:");
        System.out.println("  java -XX:+UseZGC -XX:+ZGenerational -jar myapp.jar");
        System.out.println();
        System.out.println("JDK 23 使用非分代模式(已废弃):");
        System.out.println("  java -XX:+UseZGC -XX:-ZGenerational -jar myapp.jar");
    }

    /**
     * 测试 ZGC 与 G1GC 的对比(STANDARD)
     * 说明 ZGC 分代模式与 G1GC 的差异
     */
    @Test
    public void testZGCvsG1GC() {
        // JDK 23 STANDARD 正式特性
        System.out.println("===== ZGC vs G1GC 对比 =====");
        System.out.println();
        System.out.println("+------------------+------------------+------------------+");
        System.out.println("| 特性             | ZGC (分代)       | G1GC             |");
        System.out.println("+------------------+------------------+------------------+");
        System.out.println("| 目标             | 低延迟           | 平衡吞吐量       |");
        System.out.println("| 暂停时间目标     | &lt;1ms           | ~100ms           |");
        System.out.println("| 适用场景         | 大堆、低延迟     | 通用场景         |");
        System.out.println("| 堆大小           | 超大堆(数百GB)   | 中等堆(4-64GB)   |");
        System.out.println("| 默认 GC (JDK 23) | 可选             | 默认             |");
        System.out.println("+------------------+------------------+------------------+");
        System.out.println();
        System.out.println("===== 选择建议 =====");
        System.out.println("使用 ZGC 的场景:");
        System.out.println("  1. 需要低延迟响应的应用(如实时交易系统)");
        System.out.println("  2. 大堆内存应用(超过 100GB)");
        System.out.println("  3. 对 GC 暂停时间敏感的应用");
        System.out.println();
        System.out.println("使用 G1GC 的场景:");
        System.out.println("  1. 通用 Java 应用");
        System.out.println("  2. 中等堆内存应用");
        System.out.println("  3. 对吞吐量要求较高的场景");
    }

    /**
     * 测试 ZGC 参数配置说明(STANDARD)
     * 演示 JDK 23 中 ZGC 的常用配置参数
     */
    @Test
    public void testZGCConfiguration() {
        // JDK 23 STANDARD 正式特性
        System.out.println("===== ZGC 常用配置参数 =====");
        System.out.println();
        System.out.println("启用 ZGC:");
        System.out.println("  -XX:+UseZGC");
        System.out.println();
        System.out.println("堆大小配置:");
        System.out.println("  -Xms4g -Xmx4g           // 固定堆大小 4GB");
        System.out.println("  -XX:MinHeapSize=512m    // 最小堆大小");
        System.out.println("  -XX:InitialHeapSize=1g  // 初始堆大小");
        System.out.println();
        System.out.println("并发线程数:");
        System.out.println("  -XX:ConcGCThreads=4     // 并发 GC 线程数");
        System.out.println("  -XX:ParallelGCThreads=8 // 并行 GC 线程数");
        System.out.println();
        System.out.println("ZGC 特定参数:");
        System.out.println("  -XX:ZAllocationSpikeTolerance=2.0  // 分配突发容忍度");
        System.out.println("  -XX:ZCollectionInterval=60         // 强制 GC 间隔(秒)");
        System.out.println("  -XX:ZFragmentationLimit=25         // 碎片率限制(%)");
        System.out.println("  -XX:ZMarkStackSpaceLimit=8m        // 标记栈空间限制");
        System.out.println();
        System.out.println("JDK 23 完整示例命令:");
        System.out.println("  java -XX:+UseZGC -Xms2g -Xmx2g -jar myapp.jar");
    }

    // 使用 ManagementFactory 需要导入
    // 由于是演示代码, 在实际使用中需要导入 java.lang.management.ManagementFactory
    // 这里使用完整限定名
}