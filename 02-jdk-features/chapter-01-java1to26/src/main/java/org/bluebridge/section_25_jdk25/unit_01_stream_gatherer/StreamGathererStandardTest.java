package org.bluebridge.section_25_jdk25.unit_01_stream_gatherer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * JDK 25 Stream Gatherers 测试(STANDARD 正式特性)
 *
 * Stream Gatherers(JEP 485) 在 JDK 25 中转正为 STANDARD 正式特性,
 * 不再需要 --enable-preview。
 *
 * Stream Gatherers 通过 Stream.gather(Gatherer) 中间操作为 Stream API
 * 提供了自定义中间操作的能力。JDK 25 内置了以下 Gatherer 实现:
 *   1. Gatherers.windowFixed(int)    - 将元素按固定大小分组为 List
 *   2. Gatherers.windowSliding(int)  - 滑动窗口, 每次滑动一个元素
 *   3. Gatherers.mapConcurrent(int, Function) - 并发映射, 限制并发数
 *   4. Gatherers.fold(Supplier, BiFunction)  - 有状态折叠操作
 *   5. Gatherers.scan(Supplier, BiFunction)  - 扫描操作, 累积中间结果
 *
 * 本类演示 JDK 25 转正后的 Stream Gatherers 全部内置特性。
 *
 * 演化历程: Stream Gatherer JDK 22(1st PREVIEW) → JDK 25(JEP 485, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class StreamGathererStandardTest {

    /**
     * 测试 Gatherers.windowFixed() 固定窗口分组(STANDARD)
     * 将流中的元素按固定大小分组为 List, 最后一个窗口可能小于指定大小
     * 类似 Guava 的 Iterables.partition()
     */
    @Test
    public void testWindowFixed() {
        List<List<Integer>> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println("windowFixed(3) 分组结果: " + numbers);
        // 输出: [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
        System.out.println("--------------------------------------");

        // 当流元素数量正好是窗口大小的整数倍
        List<List<Integer>> exact = Stream.of(1, 2, 3, 4, 5, 6)
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println("windowFixed(3) 整倍数: " + exact);
        // 输出: [[1, 2, 3], [4, 5, 6]]
        System.out.println("--------------------------------------");

        // 单个元素分组
        List<List<Integer>> single = Stream.of(1)
                .gather(Gatherers.windowFixed(2))
                .toList();
        System.out.println("windowFixed(2) 单元素: " + single);
        // 输出: [[1]]
    }

    /**
     * 测试 Gatherers.windowSliding() 滑动窗口(STANDARD)
     * 将流中的元素按滑动窗口分组, 每次向后滑动一个元素
     * 每个窗口大小固定, 最后一个窗口可能小于指定大小
     */
    @Test
    public void testWindowSliding() {
        List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(3))
                .toList();
        System.out.println("windowSliding(3) 滑动窗口: " + windows);
        // 输出: [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
        System.out.println("--------------------------------------");

        // 窗口大小大于流长度
        List<List<Integer>> shortWindows = Stream.of(1, 2)
                .gather(Gatherers.windowSliding(3))
                .toList();
        System.out.println("windowSliding(3) 短流: " + shortWindows);
        // 输出: [[1, 2]]
        System.out.println("--------------------------------------");

        // 滑动窗口在移动平均计算中的应用
        List<Double> prices = List.of(10.0, 11.0, 12.0, 13.0, 14.0, 15.0);
        List<Double> movingAverages = prices.stream()
                .gather(Gatherers.windowSliding(3))
                .map(window -> window.stream().mapToDouble(Double::doubleValue).average().orElse(0))
                .toList();
        System.out.println("原始价格: " + prices);
        System.out.println("3日移动平均: " + movingAverages);
    }

    /**
     * 测试 Gatherers.mapConcurrent() 并发映射(STANDARD)
     * 使用指定最大并发数对元素进行并发映射操作
     * 适用于 I/O 密集型任务, 如并行发起 HTTP 请求
     */
    @Test
    public void testMapConcurrent() {
        // 模拟并发处理任务, 最大并发数为 3
        List<String> processed = Stream.of("A", "B", "C", "D", "E", "F")
                .gather(Gatherers.mapConcurrent(3, element -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("处理元素 " + element + " 在线程: " + threadName);
                    try {
                        // 模拟耗时操作
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "处理后的[" + element + "]";
                }))
                .toList();
        System.out.println("并发处理结果: " + processed);
        System.out.println("--------------------------------------");

        // 验证结果顺序与输入顺序一致
        List<Integer> ordered = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.mapConcurrent(2, i -> i * i))
                .toList();
        System.out.println("mapConcurrent 平方结果: " + ordered);
        System.out.println("结果顺序与输入顺序一致: " + ordered.equals(List.of(1, 4, 9, 16, 25)));
    }

    /**
     * 测试 Gatherers.fold() 有状态折叠操作(STANDARD)
     * fold 类似于 reduce, 但允许在每次处理元素时维护一个可变的状态容器
     * 第一个参数提供初始状态, 第二个参数定义如何将元素合并到状态中
     */
    @Test
    public void testFold() {
        // 使用 fold 将数字分组为奇数和偶数
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var result = numbers.stream()
                .gather(Gatherers.fold(() -> {
                            List<List<Integer>> acc = new java.util.ArrayList<>();
                            acc.add(new java.util.ArrayList<>()); // 奇数
                            acc.add(new java.util.ArrayList<>()); // 偶数
                            return acc;
                        },
                        (acc, element) -> {
                            if (element % 2 == 0) {
                                acc.get(1).add(element);
                            } else {
                                acc.get(0).add(element);
                            }
                            return acc;
                        }))
                .findFirst()
                .orElse(new ArrayList<>());
        System.out.println("fold 奇偶分组: " + result);
        System.out.println("  奇数: " + result.get(0));
        System.out.println("  偶数: " + result.get(1));
        System.out.println("--------------------------------------");

        // 使用 fold 实现字符串拼接
        String concatenated = Stream.of("Hello", " ", "World", "!")
                .gather(Gatherers.fold(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str)))
                .map(StringBuilder::toString)
                .findFirst()
                .orElse("");
        System.out.println("fold 字符串拼接: '" + concatenated + "'");
    }

    /**
     * 测试 Gatherers.scan() 扫描操作(STANDARD)
     * scan 类似于 fold, 但会输出每个中间结果, 类似于 Stream 的 peek 但有状态
     * 常用于计算累积和、运行总数等
     */
    @Test
    public void testScan() {
        // 使用 scan 计算累积和
        List<Integer> runningTotal = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.scan(() -> 0, (sum, element) -> sum + element))
                .toList();
        System.out.println("原始数据: [1, 2, 3, 4, 5]");
        System.out.println("scan 累积和: " + runningTotal);
        // 输出: [1, 3, 6, 10, 15]
        System.out.println("--------------------------------------");

        // 使用 scan 计算运行最大值
        List<Integer> runningMax = Stream.of(3, 1, 7, 2, 9, 4)
                .gather(Gatherers.scan(() -> Integer.MIN_VALUE, Math::max))
                .toList();
        System.out.println("原始数据: [3, 1, 7, 2, 9, 4]");
        System.out.println("scan 运行最大值: " + runningMax);
        // 输出: [3, 3, 7, 7, 9, 9]
    }

    /**
     * 测试组合使用多种 Gatherer(STANDARD)
     * 可以在一个流中串联多个 gather 操作, 实现复杂的数据处理管道
     */
    @Test
    public void testCombinedGatherers() {
        // 先并发处理, 再窗口分组
        var result = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .gather(Gatherers.mapConcurrent(4, s -> "[" + s + "]"))
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println("mapConcurrent + windowFixed 组合: " + result);
        System.out.println("--------------------------------------");

        // 先滑动窗口, 再处理每个窗口
        var slidingResult = Stream.of(10, 20, 30, 40, 50)
                .gather(Gatherers.windowSliding(2))
                .map(window -> {
                    int first = window.get(0);
                    int second = window.get(1);
                    return first + " + " + second + " = " + (first + second);
                })
                .toList();
        System.out.println("windowSliding 组合: " + slidingResult);
    }

    /**
     * 测试不同 Gatherer 的实际应用场景(STANDARD)
     * 展示 Gatherer 在数据处理中的实际用途
     */
    @Test
    public void testGathererUseCases() {
        // 场景1: 批量处理数据(每批处理完成后输出)
        System.out.println("=== 场景1: 批量数据处理 ===");
        List<Integer> batchResults = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3))
                .map(batch -> {
                    int sum = batch.stream().mapToInt(Integer::intValue).sum();
                    System.out.println("  处理批次 " + batch + " 总和: " + sum);
                    return sum;
                })
                .toList();
        System.out.println("批次结果: " + batchResults);
        System.out.println("--------------------------------------");

        // 场景2: 数据去重滑动比较
        System.out.println("=== 场景2: 滑动比较 ===");
        var changes = Stream.of(100, 102, 105, 103, 108, 110)
                .gather(Gatherers.windowSliding(2))
                .map(window -> {
                    int prev = window.get(0);
                    int curr = window.get(1);
                    int diff = curr - prev;
                    String direction = diff > 0 ? "↑" : (diff < 0 ? "↓" : "→");
                    return prev + " -> " + curr + " (" + direction + diff + ")";
                })
                .toList();
        System.out.println("变化趋势: " + changes);
    }
}