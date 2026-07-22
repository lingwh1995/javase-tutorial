package org.bluebridge.java9.section_06_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java9 Stream 测试
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class StreamTest {

    /**
     * takeWhile测试 在有序的Stream中，takeWhile从头开始返回满足条件的元素
     */
    @Test
    public void testTakeWhile() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().takeWhile(x -> x < 5).forEach(System.out::println);
    }

    /**
     * dropWhile测试 在有序的Stream中，dropWhile返回除了从头开始满足条件的元素
     */
    @Test
    public void testDropWhile() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().dropWhile(x -> x < 5).forEach(System.out::println);
    }

    /**
     * Java8中Stream不能完全为null，否则会报空指针异常。Java9中 新增加的Stream.ofNullable()
     *
     * 方法允许我们创建一个只包含单个 null 的Stream
     */
    @Test
    public void testStreamOfNullable() {
        // of() 参数中的多个元素，可以包含 null 值
        Stream.of(1, 2, 3, 4, null).forEach(System.out::println);
        System.out.println("----------------------------");

        // of() 参数中不能只包含 null ,会报空指针异常
        // Stream.of(null).forEach(System.out::println);
        System.out.println("----------------------------");

        Stream.ofNullable(null).forEach(System.out::println);
        System.out.println("----------------------------");
    }

    /**
     * 创建无限流 java9提供了 iterate() 的一个重载方法，这个重载方法提供了新的方式中止无限流
     */
    @Test
    public void testStreamIterate() {
        // java8提供的Stream创建无限流的方法
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
        System.out.println("----------------------------");

        // java9提供的Stream创建无限流的方法
        Stream.iterate(0, x -> x < 100, x -> x + 1).limit(10).forEach(System.out::println);
        System.out.println("----------------------------");
    }
}
