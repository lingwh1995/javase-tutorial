package org.bluebridge.java8.chapter_05_stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Stream流中止操作
 * @date 2026/7/9 00:00
 */
public class StreamEndTest {
    private List<Employee> employees;

    /**
     * 初始化集合
     */
    @Before
    public void before() {
        Supplier<List<Employee>> supplier = ArrayList::new;
        List<Employee> employees = supplier.get();
        employees.add(new Employee(1l, "张三", 10, new BigDecimal(1000.5)));
        employees.add(new Employee(2l, "李四四", 20, new BigDecimal(1500.5)));
        employees.add(new Employee(3l, "王五", 15, new BigDecimal(2000.5)));
        employees.add(new Employee(4l, "赵六", 25, new BigDecimal(2500.5)));
        employees.add(new Employee(5l, "赵六", 25, new BigDecimal(2500.5)));
        this.employees = employees;
    }

    /**
     * 遍历流，按顺序输出流中元素
     */
    @Test
    public void testStreamForeach() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        // stream.forEach(employee -> System.out.println(employee));
        stream.forEach(System.out::println);
    }

    /**
     * 遍历流，按顺序输出流中元素，即使在并行流中也是如此
     */
    @Test
    public void testStreamForEachOrdered() {
        // 创建顺序流
        Stream<Employee> stream = employees.stream();
        // 按顺序输出流中元素
        stream.forEachOrdered(System.out::println);

        // 创建并行流
        Stream<Employee> parallelStream = employees.parallelStream();
        // 按顺序输出流中元素
        parallelStream.forEachOrdered(System.out::println);
    }

    /**
     * 判断流中的所有元素是否都满足给定的条件
     */
    @Test
    public void testStreamAllMatch() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        boolean allMatch = stream.allMatch(employee -> employee.getAge() > 15);
        System.out.println(allMatch);
    }

    /**
     * 判断流中的任意一个元素是否满足给定的条件。
     */
    @Test
    public void testStreamAnyMatch() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        boolean anyMatch = stream.anyMatch(employee -> employee.getAge() > 15);
        System.out.println(anyMatch);
    }

    /**
     * 判断流中的所有元素是否都不满足给定的条件
     */
    @Test
    public void testStreamNoneMatch() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        boolean noneMatch = stream.noneMatch(employee -> employee.getAge() > 15);
        System.out.println(noneMatch);
    }

    /**
     * 返回当前流中第一个元素
     */
    @Test
    public void testStreamFindFirst() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        Optional<Employee> first = stream.findFirst();
        System.out.println(first.get());
    }

    /**
     * 返回流中的任意一个元素，特别适用于并行流。在并行流中，findAny()可能比findFirst()更高效，因为它可以直接返回找到的第一个可用结果。
     */
    @Test
    public void testStreamFindAny() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        Optional<Employee> any = stream.findAny();
        System.out.println(any.get());
    }

    /**
     * 计算当前流中元素总个数
     */
    @Test
    public void testStreamCount() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        long count = stream.filter(employee -> employee.getAge() > 15).count();
        System.out.println(count);
    }

    /**
     * 获取计算当前流中值最大的元素
     */
    @Test
    public void testStreamMax() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        // Optional<Employee> maxEmployee = stream.max((e1, e2) ->
        // Double.compare(e1.getSalary().doubleValue(), e2.getSalary().doubleValue()));
        Optional<Employee> maxEmployee =
                stream.max(Comparator.comparingDouble(e -> e.getSalary().doubleValue()));
        System.out.println(maxEmployee.get());

        Optional<Integer> maxInteger = Arrays.asList(1, 2, 3, 4, 5, 6).stream().max(Integer::compareTo);
        System.out.println(maxInteger.get());
    }

    /**
     * 获取计算当前流中值最小的元素
     */
    @Test
    public void testStreamMin() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        // Optional<Employee> minEmployee = stream.min((e1, e2) ->
        // Double.compare(e1.getSalary().doubleValue(), e2.getSalary().doubleValue()));
        Optional<Employee> minEmployee =
                stream.min(Comparator.comparingDouble(e -> e.getSalary().doubleValue()));
        System.out.println(minEmployee.get());

        Optional<Integer> minInteger = Arrays.asList(1, 2, 3, 4, 5, 6).stream().min(Integer::compareTo);
        System.out.println(minInteger.get());
    }

    /**
     * 归并计算 可以将流中的元素反复结合起来，得到一个值，然后返回
     */
    @Test
    public void testStreamReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 使用有 identity 参数的 reduce
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum); // 输出: 15
        System.out.println("----------------------");

        // 使用没有 identity 的 reduce
        Optional<Integer> sumWithoutIdentity = numbers.stream().reduce(Integer::sum);
        sumWithoutIdentity.ifPresent(System.out::println); // 输出: 15
        System.out.println("----------------------");

        List<String> words = Arrays.asList("Hello", "World", "Java");
        String result = words.stream().reduce("", (a, b) -> a + " " + b);
        System.out.println(result); // 输出: " Hello World Java"
        System.out.println("----------------------");
    }

    @Test
    public void testStreamSummaryStatistics() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // 使用 mapToInt 将 Integer 转为 int
        IntSummaryStatistics stats = numbers.stream().mapToInt(Integer::intValue).summaryStatistics();

        System.out.println("Max: " + stats.getMax()); // 输出: Max: 9
        System.out.println("Min: " + stats.getMin()); // 输出: Min: 1
        System.out.println("Sum: " + stats.getSum()); // 输出: Sum: 45
        System.out.println("Average: " + stats.getAverage()); // 输出: Average: 5.0
        System.out.println("Count: " + stats.getCount()); // 输出: Count: 9
    }
}
