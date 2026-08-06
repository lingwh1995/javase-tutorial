﻿package org.bluebridge.section_13_jdk13.unit_04_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JDK 13 Stream 相关测试
 *
 * 1. JDK 13 未对 Stream API 引入新的公开方法, 本类测试现有 Stream API 在 JDK 13 下的行为
 * 2. Stream API 演进时间线:
 *     - JDK 8: 引入 Stream API
 *     - JDK 9: 新增 takeWhile()、dropWhile()、ofNullable()、iterate() 重载
 *     - JDK 16: 新增 toList()
 *     - JDK 17: 新增 mapMulti()
 * 3. 本类演示: 创建流、中间操作(过滤/映射/去重/排序/截断/跳过)、终止操作(归约/收集)
 *
 * @author lingwh
 * @date 2026/08/05 18:29
 */
public class StreamNewMethodsTest {

    /**
     * 测试 Stream 过滤与映射操作: filter + map + collect
     */
    @Test
    public void testStreamFilterAndMap() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 过滤出偶数, 再映射为 "偶数: n" 字符串并收集到 List
        List<String> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> "偶数: " + n)
                .collect(Collectors.toList());
        System.out.println("过滤 + 映射结果: " + result);
    }

    /**
     * 测试 Stream 去重、排序、截断与跳过操作: distinct + sorted + limit + skip
     */
    @Test
    public void testStreamDistinctSortedLimitSkip() {
        List<Integer> numbers = Arrays.asList(5, 3, 5, 1, 3, 4, 2, 1);
        // 去重后升序排序
        List<Integer> distinctSorted = numbers.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println("去重 + 排序: " + distinctSorted);
        // 截断: 排序后只取前 3 个
        List<Integer> limited = numbers.stream().distinct().sorted().limit(3).collect(Collectors.toList());
        System.out.println("去重 + 排序 + 截断前 3 个: " + limited);
        // 跳过: 排序后跳过前 2 个
        List<Integer> skipped = numbers.stream().distinct().sorted().skip(2).collect(Collectors.toList());
        System.out.println("去重 + 排序 + 跳过前 2 个: " + skipped);
    }

    /**
     * 测试 Stream 归约操作: reduce 求和、求最大值
     */
    @Test
    public void testStreamReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // reduce 求和
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("reduce 求和: " + sum);
        // reduce 求最大值
        int max = numbers.stream().reduce(Integer.MIN_VALUE, Math::max);
        System.out.println("reduce 求最大值: " + max);
    }

    /**
     * 测试 Stream 收集操作: 拼接、分组、计数
     */
    @Test
    public void testStreamCollect() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva");
        // 收集为以逗号分隔的字符串
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println("join 收集: " + joined);
        // 按名字长度分组
        Map<Integer, List<String>> groupByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("按名字长度分组: " + groupByLength);
        // 统计名字数量
        long count = names.stream().count();
        System.out.println("名字数量: " + count);
    }
}
