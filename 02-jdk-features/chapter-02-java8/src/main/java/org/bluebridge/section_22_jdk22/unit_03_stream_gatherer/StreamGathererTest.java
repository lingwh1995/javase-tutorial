package org.bluebridge.section_22_jdk22.unit_03_stream_gatherer;

import org.junit.Test;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * JDK 22 Stream Gatherers 测试(PREVIEW 预览特性)
 *
 * Stream Gatherers(JEP 461) 是 JDK 22 的 PREVIEW 预览特性, 第一次预览。
 * 引入 Stream::gather 方法和 Gatherers 工具类, 提供了自定义中间操作的能力。
 * 内置 Gatherers 包括: windowFixed, windowSliding, mapConcurrent, fold 等。
 *
 * 注意: 本文件使用 JDK 22 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 22 StreamGathererTest.java
 *       运行命令: java --enable-preview StreamGathererTest
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class StreamGathererTest {

    /**
     * 测试 Gatherers.windowFixed 固定窗口大小分组(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * windowFixed 将流元素按固定大小分组, 每组作为一个 List
     */
    @Test
    public void testWindowFixed_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 将元素按 3 个一组分组
        List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println("windowFixed(3) 分组结果:");
        windows.forEach(System.out::println);
        System.out.println("--------------------------------------");

        // 窗口大小大于元素数量
        List<List<Integer>> smallWindows = Stream.of(1, 2)
                .gather(Gatherers.windowFixed(5))
                .toList();
        System.out.println("windowFixed(5) 对 2 个元素分组:");
        smallWindows.forEach(System.out::println);
    }

    /**
     * 测试 Gatherers.windowSliding 滑动窗口分组(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * windowSliding 将流元素按滑动窗口分组, 步长为 1
     */
    @Test
    public void testWindowSliding_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用滑动窗口, 每 3 个元素一组, 步长为 1
        List<List<Integer>> sliding = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(3))
                .toList();
        System.out.println("windowSliding(3) 滑动窗口结果:");
        sliding.forEach(System.out::println);
        System.out.println("--------------------------------------");

        // 窗口大小为 2
        List<List<Integer>> sliding2 = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.windowSliding(2))
                .toList();
        System.out.println("windowSliding(2) 滑动窗口结果:");
        sliding2.forEach(System.out::println);
    }

    /**
     * 测试 Gatherers.mapConcurrent 并发映射(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * mapConcurrent 使用虚拟线程并发执行映射操作
     */
    @Test
    public void testMapConcurrent_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 并发处理元素, 最大并发数为 4
        List<String> result = Stream.of("A", "B", "C", "D", "E")
                .gather(Gatherers.mapConcurrent(4, s -> {
                    // 模拟耗时操作
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "处理: " + s;
                }))
                .toList();
        System.out.println("mapConcurrent 并发处理结果:");
        result.forEach(System.out::println);
        System.out.println("--------------------------------------");

        // 默认最大并发数
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .gather(Gatherers.mapConcurrent(2, n -> n * n))
                .toList();
        System.out.println("mapConcurrent 平方计算: " + numbers);
    }

    /**
     * 测试 Gatherers.fold 折叠操作(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * fold 类似于 reduce, 但支持并行折叠
     */
    @Test
    public void testFold_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用 fold 实现字符串拼接
        String folded = Stream.of("A", "B", "C", "D")
                .gather(Gatherers.fold(() -> "", (acc, element) -> acc + element))
                .findFirst()
                .orElse("");
        System.out.println("fold 拼接结果: " + folded);
        System.out.println("--------------------------------------");

        // 使用 fold 实现累加
        List<Integer> foldedNumbers = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.fold(() -> 0, (acc, n) -> acc + n))
                .toList();
        System.out.println("fold 累加结果: " + (foldedNumbers.isEmpty() ? 0 : foldedNumbers.get(foldedNumbers.size() - 1)));
    }

    /**
     * 测试 Gatherers 的链式组合使用(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 多个 gather 操作可以链式组合
     */
    @Test
    public void testChainedGatherers_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 先用 windowFixed 分组, 再处理每组数据
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .gather(Gatherers.windowFixed(3))
                .map(window -> window.stream().mapToInt(Integer::intValue).sum())
                .toList();
        System.out.println("windowFixed(3) 后每组求和: " + numbers);
        System.out.println("--------------------------------------");

        // 使用 gather 过滤掉空窗口
        List<List<Integer>> nonEmptyWindows = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowFixed(2))
                .gather(Gatherers.windowFixed(1))
                .toList();
        System.out.println("链式 gather 结果: " + nonEmptyWindows);
    }

    /**
     * 测试自定义 Gatherer(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 Stream::gather 结合 Gatherers 内置实现进行自定义操作
     */
    @Test
    public void testCustomGathererUsage_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用 mapConcurrent 处理并限制并发
        List<String> processed = Stream.of("java", "python", "go", "rust", "kotlin")
                .gather(Gatherers.mapConcurrent(3, s -> s.toUpperCase()))
                .toList();
        System.out.println("并发转大写: " + processed);
        System.out.println("--------------------------------------");

        // 使用 windowFixed 实现批处理
        List<List<String>> batches = Stream.of("A", "B", "C", "D", "E", "F", "G")
                .gather(Gatherers.windowFixed(4))
                .toList();
        System.out.println("批量处理(每批 4 个): " + batches);
    }
}