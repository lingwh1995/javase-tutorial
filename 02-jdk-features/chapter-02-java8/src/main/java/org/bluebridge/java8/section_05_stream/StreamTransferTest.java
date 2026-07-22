package org.bluebridge.java8.section_05_stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 将Stream转换为数组或者集合
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class StreamTransferTest {

    /**
     * 将Stream转换成数组
     */
    @Test
    public void testStreamTransferToArray() {
        // 创建流
        Stream<String> stream = Stream.of("a", "b", "c");
        // lambda表达式写法将Stream转换成数组
        String[] array = stream.toArray(n -> new String[n]);
        Arrays.asList(array).forEach(e -> System.out.println(e));
        System.out.println("------------------");

        // 方法引用写法
        // 创建流
        stream = Stream.of("a", "b", "c");
        // 方法引用式写法将Stream转换成数组
        array = stream.toArray(String[]::new);
        Arrays.asList(array).forEach(System.out::println);
        System.out.println("------------------");
    }

    /**
     * 将Stream转换成集合
     */
    @Test
    public void testStreamTransferToCollection() {
        // 创建流
        Stream<String> stream = Stream.of("a", "b", "c");
        // lambda表达式将Stream流转换为集合
        List<String> collect = stream.collect(Collectors.toList());
        collect.forEach(e -> System.out.println(e));
        System.out.println("------------------");

        // 创建流
        stream = Stream.of("a", "b", "c");
        // 方法引用将Stream流转换为集合
        collect = stream.collect(Collectors.toCollection(ArrayList::new));
        collect.forEach(System.out::println);
        System.out.println("------------------");
    }

    /**
     * 将Stream转换成字符串
     */
    @Test
    public void testStreamTransferToString() {
        // 创建流
        Stream<String> stream = Stream.of("a", "b", "c");
        // 将流转换为字符串
        String string = stream.collect(Collectors.joining(",")).toString();
        System.out.println(string);
    }

    /**
     * 操作stream中的数据不会影响集合中的数据，这是非常重要的特性
     */
    @Test
    public void testStreamAndMemory() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list = " + list);
        List<String> listFromStream = list.stream().collect(Collectors.toList());
        listFromStream.remove(0);
        System.out.println("listFromStream = " + listFromStream);
        System.out.println("list = " + list);
    }
}
