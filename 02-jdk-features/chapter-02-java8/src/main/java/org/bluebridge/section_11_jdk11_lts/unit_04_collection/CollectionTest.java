package org.bluebridge.section_11_jdk11_lts.unit_04_collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

/**
 * JDK 11 集合新方法测试
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class CollectionTest {

    /**
     * 测试 List.of() 方法
     * 创建不可变列表（JDK 9 引入，JDK 11 中正式使用）
     */
    @Test
    public void testListOf() {
        // 创建空列表
        List<String> emptyList = List.of();
        System.out.println("空列表: " + emptyList);

        // 创建包含元素的列表
        List<String> list = List.of("Java", "JDK", "11");
        System.out.println("List.of 创建: " + list);

        // 验证列表不可变
        try {
            list.add("New");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of 创建的列表不可变，抛出: " + e.getClass().getSimpleName());
        }
    }

    /**
     * 测试 Set.of() 方法
     * 创建不可变集合（JDK 9 引入，JDK 11 中正式使用）
     */
    @Test
    public void testSetOf() {
        // 创建空集合
        Set<String> emptySet = Set.of();
        System.out.println("空集合: " + emptySet);

        // 创建包含元素的集合
        Set<String> set = Set.of("Java", "JDK", "11", "LTS");
        System.out.println("Set.of 创建: " + set);

        // 验证集合不可变
        try {
            set.add("New");
        } catch (UnsupportedOperationException e) {
            System.out.println("Set.of 创建的集合不可变，抛出: " + e.getClass().getSimpleName());
        }
    }

    /**
     * 测试 Map.of() 方法
     * 创建不可变映射（JDK 9 引入，JDK 11 中正式使用）
     */
    @Test
    public void testMapOf() {
        // 创建空映射
        Map<String, Integer> emptyMap = Map.of();
        System.out.println("空映射: " + emptyMap);

        // 创建包含键值对的映射（最多支持 10 对键值）
        Map<String, Integer> map = Map.of("Java", 8, "JDK", 11, "LTS", 17);
        System.out.println("Map.of 创建: " + map);

        // 验证映射不可变
        try {
            map.put("New", 21);
        } catch (UnsupportedOperationException e) {
            System.out.println("Map.of 创建的映射不可变，抛出: " + e.getClass().getSimpleName());
        }
    }

    /**
     * 测试 Collection.toArray(IntFunction) 方法
     * 使用 IntFunction 指定数组类型，将集合转换为数组
     */
    @Test
    public void testToArrayWithIntFunction() {
        // 准备集合
        List<String> list = Arrays.asList("Java", "JDK", "11", "LTS");

        // JDK 11 新方式：使用 IntFunction 指定数组类型
        String[] array = list.toArray(String[]::new);
        System.out.println("toArray 数组长度: " + array.length);
        for (String s : array) {
            System.out.println("元素: " + s);
        }

        // 对比传统方式
        String[] oldWay = list.toArray(new String[0]);
        System.out.println("传统方式数组长度: " + oldWay.length);
    }

    /**
     * 测试 Map.ofEntries() 方法
     * 创建包含任意数量键值对的不可变映射
     */
    @Test
    public void testMapOfEntries() {
        // 使用 Map.entry() 创建多个键值对
        Map<String, Integer> map = Map.ofEntries(
                Map.entry("Java", 8),
                Map.entry("JDK", 11),
                Map.entry("LTS", 17),
                Map.entry("Spring", 5)
        );
        System.out.println("Map.ofEntries 创建: " + map);
        System.out.println("键值对数量: " + map.size());
    }
}