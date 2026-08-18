package org.bluebridge.section_09_jdk9.unit_06_optional;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java9 Optional 改进测试
 *
 * Java9 为 Optional 新增了以下方法:
 * 1. Optional.ifPresentOrElse(Consumer, Runnable): 有值则执行 Consumer, 无值则执行 Runnable
 * 2. Optional.stream(): 将 Optional 转换为 Stream, 方便与 Stream API 链式调用
 * 3. Optional.or(Supplier): 当 Optional 为空时, 通过 Supplier 提供替代的 Optional
 *
 * 演化历程: Optional 改进 JDK 9 STANDARD（JEP 213）
 *
 * @author lingwh
 * @date 2026/08/06 14:06
 */
public class OptionalImprovementsTest {

    /**
     * 测试 Optional.ifPresentOrElse(): 有值执行 Consumer, 无值执行 Runnable
     */
    @Test
    public void testIfPresentOrElse() {
        // Optional 有值时, 执行 Consumer
        Optional<String> optionalWithValue = Optional.of("hello");
        optionalWithValue.ifPresentOrElse(
                value -> System.out.println("有值, 值为: " + value),
                () -> System.out.println("无值, 执行默认操作")
        );
        // Optional 为空时, 执行 Runnable
        Optional<String> optionalEmpty = Optional.empty();
        optionalEmpty.ifPresentOrElse(
                value -> System.out.println("有值, 值为: " + value),
                () -> System.out.println("无值, 执行默认操作")
        );
    }

    /**
     * 测试 Optional.stream(): 将 Optional 转为 Stream, 便于链式处理
     */
    @Test
    public void testStream() {
        // 有值的 Optional 转换为包含 1 个元素的 Stream
        Optional<String> optional = Optional.of("hello");
        List<String> list1 = optional.stream().collect(Collectors.toList());
        System.out.println("Optional.of(\"hello\") 转为 Stream 收集: " + list1);
        // 空 Optional 转换为空 Stream
        Optional<String> emptyOptional = Optional.empty();
        List<String> list2 = emptyOptional.stream().collect(Collectors.toList());
        System.out.println("Optional.empty() 转为 Stream 收集: " + list2);
        // 实际应用: 结合 flatMap 过滤出所有有值的 Optional
        List<Optional<Integer>> optionalList = List.of(
                Optional.of(1), Optional.empty(), Optional.of(3), Optional.empty());
        List<Integer> numbers = optionalList.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("flatMap 过滤空 Optional 后的结果: " + numbers);
    }

    /**
     * 测试 Optional.or(): 当 Optional 为空时, 通过 Supplier 提供替代的 Optional
     */
    @Test
    public void testOr() {
        // Optional 有值时, 直接返回原值, 不执行 Supplier
        Optional<String> optionalWithValue = Optional.of("hello");
        Optional<String> result1 = optionalWithValue.or(() -> Optional.of("默认值"));
        System.out.println("有值时的 or() 结果: " + result1.get());
        // Optional 为空时, 执行 Supplier 获取替代的 Optional
        Optional<String> optionalEmpty = Optional.empty();
        Optional<String> result2 = optionalEmpty.or(() -> Optional.of("默认值"));
        System.out.println("无值时的 or() 结果: " + result2.get());
        // Supplier 也可以返回空 Optional
        Optional<String> result3 = optionalEmpty.or(() -> Optional.empty());
        System.out.println("Supplier 返回空 Optional: " + result3);
    }

    /**
     * 测试 ifPresentOrElse() 与 or() 组合使用: 实现完整的值处理链路
     */
    @Test
    public void testCombinedUsage() {
        // 1. 先从外部源获取 Optional
        Optional<String> optional = lookupValue(true);
        // 2. 使用 or() 提供兜底 Optional
        Optional<String> withDefault = optional.or(() -> Optional.of("兜底值"));
        // 3. 使用 ifPresentOrElse() 消费或执行默认操作
        withDefault.ifPresentOrElse(
                value -> System.out.println("最终处理的值: " + value),
                () -> System.out.println("无可处理的值")
        );
    }

    /**
     * 模拟外部数据源查找
     */
    private Optional<String> lookupValue(boolean found) {
        return found ? Optional.of("查找到的值") : Optional.empty();
    }

    /**
     * 测试 Optional.stream() 在复杂 Stream 链式操作中的应用
     */
    @Test
    public void testStreamInComplexChain() {
        // 模拟从多个数据源查找, 只处理有值的部分
        List<Optional<String>> sources = List.of(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Python"),
                Optional.of("Go"),
                Optional.empty()
        );
        // 使用 stream() 过滤出非空值, 并转为大写
        List<String> result = sources.stream()
                .flatMap(Optional::stream)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("过滤空值并转为大写: " + result);
    }

    /**
     * 测试 or() 与 filter() 链式调用: 实现值验证与兜底
     */
    @Test
    public void testOrWithFilter() {
        // 值存在但不符合条件时, 使用 or() 提供兜底
        Optional<String> result = Optional.of("short")
                .filter(s -> s.length() > 10)
                .or(() -> Optional.of("默认长字符串"));
        System.out.println("filter 过滤后 or() 兜底结果: " + result.get());
        // 值存在且符合条件时, 直接返回值
        Optional<String> result2 = Optional.of("very long string here")
                .filter(s -> s.length() > 10)
                .or(() -> Optional.of("默认长字符串"));
        System.out.println("filter 通过后直接返回结果: " + result2.get());
    }
}