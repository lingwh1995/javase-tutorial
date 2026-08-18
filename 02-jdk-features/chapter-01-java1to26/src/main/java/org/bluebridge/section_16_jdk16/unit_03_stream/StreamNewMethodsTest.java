package org.bluebridge.section_16_jdk16.unit_03_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * JDK 16 Stream 新增方法测试(STANDARD 正式特性)
 *
 * JDK 16 为 Stream API 新增了以下方法, 不再需要 --enable-preview 参数:
 * 1. Stream.toList(): 将流收集为不可修改的 List, 与 Collectors.toList() 相比语义更简洁
 * 2. Stream.mapMulti(): 类似 flatMap 的中间操作, 支持通过 Consumer 向下游发射 0 个或多个元素
 *
 * 本类同时演示 Stream.toArray(IntFunction) 的数组收集用法(自 JDK 8 起可用)。
 *
 * 演化历程: Stream.toList()/mapMulti() JDK 16 STANDARD（Stream API 增强）
 *
 * @author lingwh
 * @date 2026/08/05 18:43
 */
public class StreamNewMethodsTest {

    /**
     * 测试 Stream.toList() 方法(STANDARD)
     * toList() 将流元素收集为不可修改的 List, 不能对结果列表执行增删改操作
     */
    @Test
    public void testStreamToList() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // ===== 旧版实现方式(JDK 16 之前): 只能使用 collect(Collectors.toList()) 收集 =====
        // List<String> list = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        // (旧版需要手动 import java.util.stream.Collectors)
        // ===== 新版实现方式(JDK 16 起): Stream.toList() 直接收集为 List, 无需额外导入 =====
        // 真实调用 Stream.toList(): 直接收集为 List
        List<String> list = names.stream().map(String::toUpperCase).toList();
        System.out.println("Stream.toList() 结果: " + list);
        // 尝试修改返回的不可变 List, 会抛出 UnsupportedOperationException
        try {
            list.add("David");
        } catch (UnsupportedOperationException e) {
            System.out.println("toList() 返回的 List 不可修改: " + e);
        }
    }

    /**
     * 测试 Stream.mapMulti() 方法(STANDARD)
     * mapMulti() 通过 BiConsumer 向下游消费者发射 0 个或多个元素, 实现一对多映射
     */
    @Test
    public void testStreamMapMulti() {
        List<String> words = Arrays.asList("java", "jdk");
        // ===== 旧版实现方式(JDK 16 之前): 使用 flatMap 实现一对多映射 =====
        // List<String> expanded = words.stream()
        //         .flatMap(word -> Stream.of(word.toLowerCase(), word.toUpperCase()))
        //         .collect(Collectors.toList());
        // ===== 新版实现方式(JDK 16 起): mapMulti() 通过 Consumer 发射多个元素, 无需创建中间流对象 =====
        // 真实调用 Stream.mapMulti(): 每个单词发射 小写 和 大写 两个元素
        List<String> expanded = words.stream()
                .<String>mapMulti((word, consumer) -> {
                    consumer.accept(word.toLowerCase());
                    consumer.accept(word.toUpperCase());
                })
                .toList();
        System.out.println("mapMulti 一对多映射结果: " + expanded);
    }

    /**
     * 测试 Stream.mapMulti() 与 flatMap() 的对比(STANDARD)
     * 两者都能实现一对多映射, mapMulti 更轻量, 无需创建中间流对象
     */
    @Test
    public void testStreamMapMultiVsFlatMap() {
        List<String> sentences = Arrays.asList("Hello World", "JDK 16");
        // 使用 flatMap: 按空格拆分为单词流
        List<String> flatMapResult = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .toList();
        System.out.println("flatMap 拆分结果: " + flatMapResult);
        // 使用 mapMulti: 等价实现
        List<String> mapMultiResult = sentences.stream()
                .<String>mapMulti((sentence, consumer) -> {
                    for (String word : sentence.split(" ")) {
                        consumer.accept(word);
                    }
                })
                .toList();
        System.out.println("mapMulti 拆分结果: " + mapMultiResult);
    }

    /**
     * 测试 Stream.toArray(IntFunction) 方法(STANDARD)
     * 通过数组构造器引用将流收集为指定类型的数组
     */
    @Test
    public void testStreamToArray() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 真实调用 Stream.toArray(IntFunction): 过滤偶数后收集为 Integer 数组
        Integer[] array = numbers.stream().filter(n -> n % 2 == 0).toArray(Integer[]::new);
        System.out.println("toArray 收集的数组: " + Arrays.toString(array));
        // 收集为 String 数组
        String[] stringArray = Stream.of("a", "b", "c").toArray(String[]::new);
        System.out.println("toArray 收集的字符串数组: " + Arrays.toString(stringArray));
    }
}
