package org.bluebridge.java8.chapter_05_stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 收集流中元素到各种集合中
 * @date 2026/7/9 00:00
 */
@Slf4j
public class StreamCollectorsTest {
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
        employees.add(new Employee(6l, null, null, null));
        this.employees = employees;
    }

    /**
     * 收集流中元素到List集合中
     */
    @Test
    public void testCollectToList() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        List<Employee> collectList = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .filter(employee -> employee.getAge() > 15)
                        .collect(Collectors.toList());
        log.debug("collectList: {}", collectList);
    }

    /**
     * 收集流中元素到ArrayList集合中
     */
    @Test
    public void testCollectToArrayList() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        ArrayList<Employee> collectArrayList = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .filter(employee -> employee.getAge() > 15)
                .collect(Collectors.toCollection(ArrayList::new));
        log.debug("collectArrayList: {}", collectArrayList);
    }

    /**
     * 收集流中元素到Set集合中
     */
    @Test
    public void testCollectToHashSet() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        Set<Employee> collectSet = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .filter(employee -> employee.getAge() > 15)
                .collect(Collectors.toSet());
        log.debug("collectSet: {}", collectSet);
    }

    /**
     * 收集流中元素到HashSet集合中
     */
    @Test
    public void testCollectToSet() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        HashSet<Employee> collectHashSet = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .filter(employee -> employee.getAge() > 15)
                .collect(Collectors.toCollection(HashSet::new));
        log.debug("collectHashSet: {}", collectHashSet);
    }

    /**
     * 收集流中元素到数组中
     */
    @Test
    public void testCollectToArray() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        Integer[] collectArray = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .map(employee -> employee.getAge())
                //.toArray(value -> new Integer[value]); // 此处的参数 value 就是 流中元素的个数
                .toArray(Integer[]::new);
        log.debug("collectArray: {}", Arrays.toString(collectArray));
    }

    /**
     * 收集流中元素到字符串中
     */
    @Test
    public void testCollectToString() {
        // 创建流
        Stream<Employee> stream = employees.stream();
        String ageStr = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .map(employee -> employee.getAge())
                .map(String::valueOf)
                .collect(Collectors.joining("-")); // 只填入分隔符
        log.debug("ageStr: {}", ageStr);

        stream = employees.stream();
        ageStr = stream
                .filter(employee -> Objects.nonNull(employee.getAge())) // 过滤掉age为null的元素
                .map(employee -> employee.getAge())
                .map(String::valueOf).collect(Collectors.joining("-", "[", "]")); // 填入分隔符和头尾
        log.debug("ageStr: {}", ageStr);
    }

    @Test
    public void testCollectToMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("id", "001");
        map.put("age", "25");
        Map<String, String> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 根据key排序
                //.sorted(Map.Entry.comparingByValue()) // 根据值排序
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 解决重复键的问题，这里简单地选择第一个元素
                        LinkedHashMap::new // 保持排序后的顺序
                ));
        log.debug("sortedMap: {}", sortedMap);
    }
}
