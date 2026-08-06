﻿package org.bluebridge.section_16_jdk16.unit_04_collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JDK 16 Collection 相关改进测试(STANDARD 正式特性)
 *
 * JDK 16 本身没有为 Collection 接口新增方法, 与集合相关的主要改进来自 Stream API:
 * 1. Stream.toList(): 新增的终止操作, 将流收集为不可修改的 List(JDK 16)
 * 2. Stream.toList() 与 Collectors.toList() 的区别: 前者返回不可变 List, 后者返回可变的 ArrayList
 * 3. 不可变 List 与可变 List 的对比: Stream.toList()、List.copyOf() 均返回不可变 List
 *
 * @author lingwh
 * @date 2026/08/05 18:43
 */
public class CollectionNewMethodsTest {

    /**
     * 测试 Stream.toList() 返回不可修改的 List(STANDARD)
     * JDK 16 的 Stream.toList() 收集结果为不可变 List, 尝试增删改会抛出 UnsupportedOperationException
     */
    @Test
    public void testStreamToListUnmodifiable() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 真实调用 Stream.toList() 收集为 List
        List<Integer> list = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("Stream.toList() 结果: " + list);
        try {
            list.add(6);
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变 List 增删改会抛异常: " + e);
        }
    }

    /**
     * 测试 Stream.toList() 与 Collectors.toList() 的区别(STANDARD)
     * toList() 返回不可变 List; Collectors.toList() 返回可变的 ArrayList
     */
    @Test
    public void testStreamToListVsCollectorsToList() {
        Stream<String> stream1 = Stream.of("a", "b", "c");
        // Stream.toList(): 返回不可修改的 List
        List<String> immutableList = stream1.toList();
        System.out.println("toList() 结果: " + immutableList + ", 类型: " + immutableList.getClass().getSimpleName());

        Stream<String> stream2 = Stream.of("a", "b", "c");
        // Collectors.toList(): 返回可修改的 ArrayList
        List<String> mutableList = stream2.collect(Collectors.toList());
        System.out.println("collect(toList()) 结果: " + mutableList + ", 类型: " + mutableList.getClass().getSimpleName());
        mutableList.add("d");
        System.out.println("Collectors.toList() 可继续 add: " + mutableList);
    }

    /**
     * 测试集合与 Stream 结合使用(STANDARD)
     * 通过集合的 stream() 方法进行过滤、排序并收集
     */
    @Test
    public void testCollectionStreamOperations() {
        List<String> names = new ArrayList<>(Arrays.asList("Tom", "Alice", "Bob", "Charlie"));
        // 集合转 Stream 过滤并排序
        List<String> sorted = names.stream()
                .filter(name -> name.length() > 3)
                .sorted()
                .toList();
        System.out.println("过滤 + 排序结果: " + sorted);
        // 集合元素统计
        long count = names.stream().filter(name -> name.startsWith("A")).count();
        System.out.println("以 A 开头的名字数量: " + count);
    }

    /**
     * 测试不可变 List 与可变 List 的对比(STANDARD)
     * Stream.toList()(JDK 16)、List.copyOf()(JDK 10) 均返回不可变 List, Collectors.toList() 返回可变 List
     */
    @Test
    public void testUnmodifiableListComparison() {
        // Stream.toList(): JDK 16 新增, 返回不可变 List
        List<String> fromStream = Stream.of("a", "b").toList();
        // List.copyOf(): 返回不可变 List
        List<String> fromCopyOf = List.copyOf(new ArrayList<>(Arrays.asList("a", "b")));
        // Collectors.toList(): 返回可变 ArrayList
        List<String> fromCollectors = Stream.of("a", "b").collect(Collectors.toList());
        System.out.println("Stream.toList() 结果类型: " + fromStream.getClass().getSimpleName());
        System.out.println("List.copyOf() 结果类型: " + fromCopyOf.getClass().getSimpleName());
        System.out.println("Collectors.toList() 结果类型: " + fromCollectors.getClass().getSimpleName());
    }
}
