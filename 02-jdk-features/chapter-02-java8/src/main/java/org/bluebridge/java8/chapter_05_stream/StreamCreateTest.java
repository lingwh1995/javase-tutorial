package org.bluebridge.java8.chapter_05_stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream流和Collection集合的区别
 *
 * 1. Collection集合是一种静态的内存数据结构，主要面向内存，而Stream流是和计算有关的，主要面向CPU实现计算
 * 2. Stream流是延迟执行的，需要结果(只有调用终止操作)的时候才执行，也可以说是惰性求值
 * 3. Stream流相当于一个中间管道，不存储数据，而是充当传递数据的管道
 * 4. Steam流支持链式操作
 * 5. Steam流操作具有短路的特性
 * 6. 通过数组创建了一个Stream流，操作流中的数据不会影响集合中的数据（非常重要的特性）
 *
 * 创建流的八种方式
 *
 * 1. 通过集合创建 集合对象.stream()/集合对象.parallelStream()
 * 2. 通过数组创建 Arrays.stream(数组)
 * 3. 通过可变参数创建 IntStream.of(可变参数列表)
 * 4. 通过可变参数创建 StreamBuilder()
 * 5. 构建一个不包容任何元素的流 Stream.empty()/IntStream.empty()
 * 6. 通过Stream.of()创建 Stream.of(集合)/Stream.of(数组)/Stream.of(可变参数列表)
 * 7. 基于"工厂函数"构建无限流
 * 8. 基于"迭代函数"构建无限流
 *    通过方法创建
 *    从文件中获得
 *
 * 顺序流和并行流
 *
 * .stream() : 创建一个顺序执行的流
 * .parallelStream() : 创建一个并行执行的流
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class StreamCreateTest {
    /**
     * 创建Steam的六种方式：第一种 通过集合创建Stream
     */
    @Test
    public void testCreateStreamByCollection() {
        /**
         * 基于Set集合对象创建流
         */
        Set<String> set = new HashSet<>();
        set.add("s1");
        set.add("s2");
        set.add("s3");
        System.out.println("------------------");
        // 顺序流
        set.stream().forEach(s -> System.out.println(s));
        System.out.println("------------------");
        // 并行流
        set.parallelStream().forEach(s -> System.out.println(s));
        System.out.println("------------------");
        System.out.println("------------------01------------------");

        /**
         * 基于List集合对象创建流
         */
        List<String> list = new ArrayList<>();
        list.add("l1");
        list.add("l2");
        list.add("l3");
        System.out.println("------------------");
        // 顺序流
        list.stream().forEach(s -> System.out.println(s));
        System.out.println("------------------");
        // 并行流
        list.parallelStream().forEach(s -> System.out.println(s));
        System.out.println("------------------");
        System.out.println("------------------02------------------");

        /**
         * 基于Map集合对象创建流
         */
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        // 注意，map集合不能直接创建stream()，如果要创建stream，可以通过下面方式进行
        System.out.println("------------------");
        // 方式一: map.keySet().stream()
        // 顺序流
        map.keySet().stream().forEach(k -> System.out.println(k));
        System.out.println("------------------");
        // 并行流
        map.keySet().parallelStream().forEach(k -> System.out.println(k));
        System.out.println("------------------");
        // 方式二: map.values().stream()
        // 顺序流
        map.values().stream().forEach(v -> System.out.println(v));
        System.out.println("------------------");
        // 并行流
        map.values().parallelStream().forEach(v -> System.out.println(v));
        System.out.println("------------------");
        // 方式三: map.entrySet().stream()
        // 顺序流
        map.entrySet().stream().forEach(e -> System.out.println("key: " + e.getKey() + "," + "value: " + e.getValue()));
        System.out.println("------------------");
        // 并行流
        map.entrySet().parallelStream()
                .forEach(e -> System.out.println("key: " + e.getKey() + "," + "value: " + e.getValue()));
        System.out.println("------------------");
        System.out.println("------------------03------------------");

        /**
         * 基于TreeSet集合对象创建流
         */
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("ts1");
        treeSet.add("ts2");
        treeSet.add("ts3");
        System.out.println("------------------");
        // 顺序流
        treeSet.stream().forEach(ts -> System.out.println(ts));
        System.out.println("------------------");
        // 并行流
        treeSet.parallelStream().forEach(ts -> System.out.println(ts));
        System.out.println("------------------");
        System.out.println("------------------04------------------");
    }

    /**
     * 创建Steam的六种方式：第二种 通过数组创建
     * .stream() : 创建一个顺序执行的流
     * .parallelStream() : 创建一个并行执行的流
     */
    @Test
    public void testCreateStreamByArray() {
        // 通过整型数组创建一个Stream对象(使用Arrays.stream()方式创建)
        IntStream intStream = Arrays.stream(new int[] { 1, 2, 3 });
        intStream.forEach(n -> System.out.println(n));
        System.out.println("------------------");

        // 3-8,不包含8
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("------------------");
        // 3-8,包含8
        IntStream.rangeClosed(3, 8).forEach(System.out::println);
        System.out.println("------------------");

        // 通过字符串数组创建一个Stream对象(使用Arrays.stream()方式创建)
        Stream<String> stream = Arrays.stream(new String[] { "a", "b", "c" });
        stream.forEach(s -> System.out.println(s));
        System.out.println("------------------");

        // 通过字符串数组创建一个Stream对象(使用Arrays.stream()方式创建)
        String[] names = { "Ken", "Jeff", "Chris", "Ellen" };
        Stream<String> namesStream = Arrays.stream(names);
        namesStream.forEach(name -> System.out.println(name));
        System.out.println("------------------");
    }

    /**
     * 创建Steam的六种方式：第三种 通过可变参数创建
     */
    @Test
    public void testCreateStreamByVariableArguments() {
        // 使用可变参数构造字符串类的流
        Stream<String> stream = Stream.of("a", "b", "c");
        stream.forEach(s -> System.out.println(s));
        System.out.println("------------------");

        // 使用可变参数构建数值流
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream.forEach(i -> System.out.println(i));
        System.out.println("------------------");
    }

    /**
     * 创建Steam的六种方式：第四种 通过StreamBuilder创建
     */
    @Test
    public void testCreateStreamByStreamBuilder() {
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> build = builder.add("a")
                .add("b")
                .add("c")
                .build();
        build.forEach(s -> System.out.println(s));
        System.out.println("------------------");
    }

    /**
     * 创建Steam的六种方式：第五种 构建一个不包容任何元素的流
     */
    @Test
    public void testCreateEmptyStream() {
        // 构建一个空的字符串流
        Stream<String> stream = Stream.empty();
        // 构建一个空的整数流
        IntStream numbers = IntStream.empty();
    }

    /**
     * 创建Steam的六种方式：第六种 通过Stream.of()创建
     */
    @Test
    public void testCreateStreamByStreamOf() {
        /**
         * Stream.of(集合)
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Stream.of(list).forEach(s -> System.out.println(s));
        System.out.println("------------------");

        /**
         * Stream.of(数组)
         */
        Integer[] array = { 1, 2, 3, 4, 5 };
        Stream.of(array).forEach(s -> System.out.println(s));
        System.out.println("------------------");

        /**
         * Stream.of(可变参数列表)
         */
        Stream.of("a", "1", "b", "2", "c", "3").forEach(s -> System.out.println(s));
        System.out.println("------------------");
    }

	/**
     * 构建流
     *
	 * 1. 使用 Stream.generate 方法生成流
	 * 2. 基于"工厂函数"构建无限流
	 *    我们可以定义一个函数，这个函数可以帮助我们构建出流的元素，这个函数可以看成是生成流元素的"元素工厂"，然后使用这个工厂生产出来的"元素"构建出流。
	 *    注意：这种方式可以构建出元素数目无限的"无限流"，因此，通常都会使用 limit() 方法限定要生成 的元素个数。
	 */
	@Test
	public void testCreateStreamByGenerate1() {
		// 定义一个随机数生成器，生成 [0, 100) 范围内的随机整数
		Supplier<Integer> intFactory = () -> (int) (Math.random() * 100);

        // 使用 Stream.generate 创建一个无限流，并限制为前 10 个元素
        Stream<Integer> stream = Stream.generate(intFactory).limit(10);

        // 打印流中的元素
        stream.forEach(System.out::println);
    }

    /**
     * 随机生成十个UUID
     */
    @Test
    public void testCreateStreamByGenerate2() {
        // lambda表达式语法
        Supplier<String> uuidFactory = () -> UUID.randomUUID().toString();
        Stream<String> generate = Stream.generate(uuidFactory).limit(10);
        generate.forEach(s -> System.out.println(s));
        System.out.println("------------------");

        // 方法引用语法
        generate = Stream.generate(UUID.randomUUID()::toString).limit(10);
        generate.forEach(System.out::println);
        System.out.println("------------------");
    }

	/**
	 * 使用 Stream.iterate 方法生成流
	 * 基于"迭代函数"构建无限流
	 *     有些流的元素需要依据它的前一个元素才能确定，对于这种情况，可以使用 Stream.iterate() 方法构建流对象
	 *
	 *  Stream.iterate(seed, UnaryOperator)	是一个静态方法，用于生成一个无限流。
	 *		seed			初始值，这里是1。
	 *		UnaryOperator	生成规则，这里是  n -> n+2
	 */
	@Test
	public void testCreateStreamByIterate() {
		// 初始值为 1
		int seed = 1;

        // 定义一个生成规则：a[n] = a[n-1] + 2
        UnaryOperator<Integer> intFactory = n -> n + 2;

        // 使用 Stream.iterate 创建一个无限流，并限制为前 10 个元素
        Stream<Integer> stream = Stream.iterate(seed, intFactory).limit(10);

        // 打印流中的元素
        stream.forEach(System.out::println);
    }
}
