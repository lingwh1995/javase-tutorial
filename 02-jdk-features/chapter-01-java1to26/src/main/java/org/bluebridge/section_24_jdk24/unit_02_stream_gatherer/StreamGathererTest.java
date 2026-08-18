package org.bluebridge.section_24_jdk24.unit_02_stream_gatherer;

import org.junit.Test;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * JDK 24 Stream Gatherers 测试(STANDARD 正式特性)
 *
 * Stream Gatherers (JEP 485) 是 JDK 24 的 STANDARD 正式特性，无需 --enable-preview。
 *
 * Stream Gatherers 引入了一种新的中间操作机制，允许自定义中间操作，
 * 同时 JDK 24 内置了以下 Gatherer 实现（java.util.stream.Gatherers）：
 *   1. Gatherers.windowFixed(int) - 将元素按固定大小分组
 *   2. Gatherers.windowSliding(int) - 滑动窗口分组
 *   3. Gatherers.mapConcurrent(int, Function) - 并发映射
 *   4. Gatherers.fold(Supplier, BiFunction) - 自定义折叠
 *
 * 注意：本文件使用 JDK 24 正式特性的真实语法编写，无需 --enable-preview。
 *
 * 演化历程: Stream Gatherer JDK 22(JEP 461, 1st PREVIEW) → JDK 23(JEP 473, 2nd) → JDK 24(JEP 485, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class StreamGathererTest {

    /**
     * 测试 Gatherers.windowFixed 固定窗口分组(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * windowFixed 将流元素按固定大小分组，每组作为一个 List
     */
    @Test
    public void testWindowFixed_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<List<Integer>> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3))
                .toList();

        System.out.println("Gatherers.windowFixed(3) 测试:");
        System.out.println("  输入: [1, 2, 3, 4, 5, 6, 7, 8]");
        System.out.println("  输出: " + numbers);
        // 输出: [[1, 2, 3], [4, 5, 6], [7, 8]]
        System.out.println("  分组数量: " + numbers.size());
        numbers.forEach(group -> System.out.println("    组: " + group));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.windowSliding 滑动窗口分组(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * windowSliding 以滑动窗口方式将元素分组，每组作为一个 List
     */
    @Test
    public void testWindowSliding_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(3))
                .toList();

        System.out.println("Gatherers.windowSliding(3) 测试:");
        System.out.println("  输入: [1, 2, 3, 4, 5]");
        System.out.println("  输出: " + windows);
        // 输出: [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
        System.out.println("  窗口数量: " + windows.size());
        windows.forEach(w -> System.out.println("    窗口: " + w));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.mapConcurrent 并发映射(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * mapConcurrent 使用固定大小的虚拟线程池并发执行映射函数
     */
    @Test
    public void testMapConcurrent_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<String> results = Stream.of("a", "bb", "ccc", "dddd", "eeeee")
                .gather(Gatherers.mapConcurrent(3, s -> {
                    String threadName = Thread.currentThread().getName();
                    return s + " -> [" + s.length() + ", thread: " + threadName + "]";
                }))
                .toList();

        System.out.println("Gatherers.mapConcurrent(3) 测试:");
        System.out.println("  输入: [\"a\", \"bb\", \"ccc\", \"dddd\", \"eeeee\"]");
        System.out.println("  并发处理结果:");
        results.forEach(System.out::println);
        System.out.println("  结果数量: " + results.size());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.fold 自定义折叠操作(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * fold 类似于 reduce，但允许保持中间状态的累积
     */
    @Test
    public void testFold_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<String> folded = Stream.of("Hello", "World", "JDK", "24", "Gatherers")
                .gather(Gatherers.fold(() -> new StringBuilder(),
                        (sb, s) -> {
                            if (sb.length() > 0) {
                                sb.append(", ");
                            }
                            sb.append(s);
                            return sb;
                        }))
                .map(StringBuilder::toString)
                .toList();

        System.out.println("Gatherers.fold 测试:");
        System.out.println("  输入: [\"Hello\", \"World\", \"JDK\", \"24\", \"Gatherers\"]");
        System.out.println("  折叠结果: " + folded);
        // 输出: ["Hello, World, JDK, 24, Gatherers"]
        folded.forEach(System.out::println);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.windowFixed 结合 map 进行分组处理(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 将流元素按 2 个一组分组，然后计算每组元素之和
     */
    @Test
    public void testWindowFixedWithMap_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<Integer> sums = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(2))
                .map(list -> list.stream().mapToInt(Integer::intValue).sum())
                .toList();

        System.out.println("windowFixed(2) + map 分组求和测试:");
        System.out.println("  输入: [1, 2, 3, 4, 5, 6, 7, 8]");
        System.out.println("  每组和: " + sums);
        // 输出: [3, 7, 11, 15]
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.windowSliding 计算移动平均(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 使用滑动窗口计算移动平均值
     */
    @Test
    public void testWindowSlidingMovingAverage_Standard() {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<Double> movingAverages = Stream.of(10, 20, 30, 40, 50, 60)
                .gather(Gatherers.windowSliding(3))
                .map(window -> window.stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0))
                .toList();

        System.out.println("滑动窗口移动平均测试:");
        System.out.println("  输入: [10, 20, 30, 40, 50, 60]");
        System.out.println("  移动平均 (窗口=3): " + movingAverages);
        // 输出: [20.0, 30.0, 40.0, 50.0]
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Gatherers.mapConcurrent 的并发性能(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 使用 mapConcurrent 模拟耗时操作，观察并发执行效果
     */
    @Test
    public void testMapConcurrentPerformance_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        List<Integer> input = Stream.iterate(1, i -> i + 1).limit(10).toList();

        long start = System.currentTimeMillis();
        List<String> result = input.stream()
                .gather(Gatherers.mapConcurrent(5, i -> {
                    // 模拟耗时操作
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "任务-" + i + " 完成于 " + Thread.currentThread().getName();
                }))
                .toList();
        long end = System.currentTimeMillis();

        System.out.println("mapConcurrent 并发性能测试:");
        System.out.println("  并发度: 5, 任务数: 10, 每个任务模拟 100ms");
        System.out.println("  总耗时: " + (end - start) + " ms");
        System.out.println("  (如果串行执行应耗时约 1000ms, 并发后应显著减少)");
        result.forEach(System.out::println);
        System.out.println("--- 分割线 ---");
    }
}