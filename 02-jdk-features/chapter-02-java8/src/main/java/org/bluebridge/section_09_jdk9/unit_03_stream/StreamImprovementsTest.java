package org.bluebridge.section_09_jdk9.unit_03_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java9 Stream 新增方法测试
 *
 * Java9 对 Stream API 进行了增强, 新增了以下方法:
 * 1. Stream.ofNullable(): 将单个元素包装为 Stream, 元素为 null 时返回空 Stream
 * 2. Stream.iterate(seed, hasNext, next): 在原有 iterate 基础上增加了终止条件
 * 3. Optional.stream(): 将 Optional 转换为 Stream, 方便与 Stream 链式调用
 * 4. Stream.takeWhile(): 从流头开始截取满足条件的元素, 遇到第一个不满足条件的元素即停止
 * 5. Stream.dropWhile(): 从流头开始丢弃满足条件的元素, 遇到第一个不满足条件的元素即停止
 *
 * 演化历程: Stream 改进 JDK 9 STANDARD（JEP 268）
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class StreamImprovementsTest {

    /**
     * 测试 Stream.ofNullable(): 元素为 null 时返回空 Stream, 避免 NPE
     */
    @Test
    public void testOfNullable() {
        // 元素不为 null, 正常生成 Stream
        Stream<String> stream1 = Stream.ofNullable("hello");
        System.out.println("ofNullable(\"hello\") 中的元素: " + stream1.collect(Collectors.toList()));
        // 元素为 null, 返回空 Stream, 不再抛出 NullPointerException
        Stream<String> stream2 = Stream.ofNullable(null);
        System.out.println("ofNullable(null) 中的元素: " + stream2.collect(Collectors.toList()));
        System.out.println("--------------------------------------");
        // 实际应用: 遍历集合时自动跳过 null 元素
        List<String> list = Arrays.asList("Java", null, "Python", null, "Go");
        List<String> result = list.stream()
                .flatMap(item -> Stream.ofNullable(item))
                .collect(Collectors.toList());
        System.out.println("过滤 null 后的结果: " + result);
    }

    /**
     * 测试 Stream.iterate(seed, hasNext, next): 带终止条件的迭代生成
     */
    @Test
    public void testIterateWithPredicate() {
        // Java8 的 iterate 只能无限生成, 需要配合 limit() 使用
        // Java9 新增的 iterate 支持传入终止条件, 不再需要 limit()
        List<Integer> result = Stream.iterate(0, i -> i < 10, i -> i + 2)
                .collect(Collectors.toList());
        System.out.println("带终止条件的 iterate 生成的结果: " + result);
    }

    /**
     * 测试 Optional.stream(): 将 Optional 转换为 Stream, 便于链式处理
     */
    @Test
    public void testOptionalStream() {
        // 有值的 Optional 转换为包含 1 个元素的 Stream
        Optional<String> optional = Optional.of("hello");
        System.out.println("Optional.of(\"hello\") 转为 Stream: " + optional.stream().collect(Collectors.toList()));
        // 空 Optional 转换为空 Stream
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Optional.empty() 转为 Stream: " + emptyOptional.stream().collect(Collectors.toList()));
        System.out.println("--------------------------------------");
        // 实际应用: 结合 flatMap 过滤出有值的 Optional
        List<Optional<Integer>> optionalList = Arrays.asList(
                Optional.of(1), Optional.empty(), Optional.of(3), Optional.empty());
        List<Integer> numbers = optionalList.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("flatMap 过滤空 Optional 后的结果: " + numbers);
    }

    /**
     * 测试 Stream.takeWhile(): 截取满足条件的元素, 遇到不满足即停止
     */
    @Test
    public void testTakeWhile() {
        // 从流头开始截取小于 5 的元素, 遇到第一个不小于 5 的元素即停止
        List<Integer> result = Stream.of(1, 2, 3, 4, 5, 6, 1, 2)
                .takeWhile(i -> i < 5)
                .collect(Collectors.toList());
        System.out.println("takeWhile(i < 5) 的结果: " + result);
    }

    /**
     * 测试 Stream.dropWhile(): 丢弃满足条件的元素, 遇到不满足即停止
     */
    @Test
    public void testDropWhile() {
        // 从流头开始丢弃小于 5 的元素, 遇到第一个不小于 5 的元素即停止
        List<Integer> result = Stream.of(1, 2, 3, 4, 5, 6, 1, 2)
                .dropWhile(i -> i < 5)
                .collect(Collectors.toList());
        System.out.println("dropWhile(i < 5) 的结果: " + result);
    }

    /**
     * 测试 takeWhile() 与 dropWhile() 配合使用, 实现按条件切割数据
     */
    @Test
    public void testTakeWhileAndDropWhile() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        // 取中间一段 [3, 6): 先丢弃小于 3 的, 再截取小于 6 的
        List<Integer> middle = list.stream()
                .dropWhile(i -> i < 3)
                .takeWhile(i -> i < 6)
                .collect(Collectors.toList());
        System.out.println("切割出的中间部分: " + middle);
    }
}
