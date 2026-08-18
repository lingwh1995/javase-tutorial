package org.bluebridge.section_12_jdk12.unit_01_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JDK 12 Stream.teeing() 收集器（STANDARD 特性）
 *      Collectors.teeing() 将两个下游收集器合并，最终通过 merger 函数处理结果
 *
 * 演化历程: Stream.teeing() JDK 12 STANDARD（JEP 461 的前身）
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class StreamTeeingTest {

    /**
     * 测试 teeing 统计学生成绩的平均值和总分
     */
    @Test
    public void testTeeingAvgAndSum() {
        List<Integer> scores = Arrays.asList(85, 92, 78, 90, 88);
        Map<String, Double> result = scores.stream()
                .collect(Collectors.teeing(
                        Collectors.averagingDouble(Integer::doubleValue),
                        Collectors.summingDouble(Integer::doubleValue),
                        (avg, sum) -> {
                            Map<String, Double> map = new HashMap<>();
                            map.put("平均值", avg);
                            map.put("总分", sum);
                            return map;
                        }
                ));
        System.out.println("teeing 统计结果: " + result);
    }

    /**
     * 测试 teeing 同时统计最大值和最小值
     */
    @Test
    public void testTeeingMaxAndMin() {
        List<Integer> numbers = Arrays.asList(23, 45, 12, 67, 34, 89, 5);
        Map<String, Integer> result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.maxBy(Integer::compareTo),
                        Collectors.minBy(Integer::compareTo),
                        (max, min) -> {
                            Map<String, Integer> map = new HashMap<>();
                            map.put("最大值", max.orElse(0));
                            map.put("最小值", min.orElse(0));
                            return map;
                        }
                ));
        System.out.println("teeing 极值统计: " + result);
    }

    /**
     * 测试 teeing 统计员工工资的平均值和总和
     */
    @Test
    public void testTeeingEmployeeSalary() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 8000.0),
                new Employee("李四", 12000.0),
                new Employee("王五", 9500.0),
                new Employee("赵六", 15000.0)
        );
        Map<String, Double> result = employees.stream()
                .collect(Collectors.teeing(
                        Collectors.averagingDouble(Employee::getSalary),
                        Collectors.summingDouble(Employee::getSalary),
                        (avg, sum) -> {
                            Map<String, Double> map = new HashMap<>();
                            map.put("平均工资", avg);
                            map.put("工资总额", sum);
                            map.put("员工人数", (double) employees.size());
                            return map;
                        }
                ));
        System.out.println("员工工资统计: " + result);
    }

    /**
     * 测试 teeing 统计布尔值分区（分区统计）
     */
    @Test
    public void testTeeingPartitionBy() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<String, Long> result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(n -> n % 2 == 0, Collectors.counting()),
                        Collectors.filtering(n -> n % 2 != 0, Collectors.counting()),
                        (evenCount, oddCount) -> {
                            Map<String, Long> map = new HashMap<>();
                            map.put("偶数个数", evenCount);
                            map.put("奇数个数", oddCount);
                            return map;
                        }
                ));
        System.out.println("奇偶数统计: " + result);
    }

    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }
    }
}