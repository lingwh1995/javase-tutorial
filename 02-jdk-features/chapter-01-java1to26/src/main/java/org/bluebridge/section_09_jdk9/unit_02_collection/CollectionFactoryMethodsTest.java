package org.bluebridge.section_09_jdk9.unit_02_collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Java9 集合工厂方法测试
 *
 * Java9 为 List/Set/Map 接口新增了 of() 和 copyOf() 静态工厂方法, 用于快速创建不可变集合:
 * 1. List.of()/Set.of()/Map.of(): 直接传入元素或键值对快速创建不可变集合
 * 2. List.copyOf()/Set.copyOf()/Map.copyOf(): 基于已有集合创建不可变副本
 * 3. 不可变集合不允许添加、删除、修改元素, 否则抛出 UnsupportedOperationException
 * 4. 不可变集合不允许包含 null 元素, 否则抛出 NullPointerException
 * 5. Set.of()/Map.of() 不允许存在重复元素/重复键, 否则抛出 IllegalArgumentException
 *
 * 演化历程: 集合工厂方法 JDK 9 STANDARD（JEP 269）
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class CollectionFactoryMethodsTest {

    /**
     * 测试 List.of() 创建不可变 List
     */
    @Test
    public void testListOf() {
        // 创建包含 3 个元素的不可变 List
        List<String> list = List.of("Java", "Python", "Go");
        System.out.println("List.of() 创建的结果: " + list);
        System.out.println("元素个数: " + list.size());
        // 支持通过索引获取元素
        System.out.println("索引为 0 的元素: " + list.get(0));
        System.out.println("索引为 2 的元素: " + list.get(2));
    }

    /**
     * 测试 Set.of() 创建不可变 Set
     */
    @Test
    public void testSetOf() {
        // 创建不可变 Set, 元素无序且不能重复
        Set<String> set = Set.of("Java", "Python", "Go");
        System.out.println("Set.of() 创建的结果: " + set);
        System.out.println("元素个数: " + set.size());
    }

    /**
     * 测试 Map.of() 创建不可变 Map
     */
    @Test
    public void testMapOf() {
        // 创建不可变 Map, 直接以键值对的形式传入
        Map<String, Integer> map = Map.of("Java", 1, "Python", 2, "Go", 3);
        System.out.println("Map.of() 创建的结果: " + map);
        System.out.println("键值对个数: " + map.size());
        System.out.println("Java 对应的值: " + map.get("Java"));
    }

    /**
     * 测试 List.copyOf() 基于已有集合创建不可变副本
     */
    @Test
    public void testListCopyOf() {
        // 先创建一个可变集合
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Java");
        mutableList.add("Python");
        // 基于可变集合创建不可变副本
        List<String> immutableList = List.copyOf(mutableList);
        System.out.println("List.copyOf() 创建的结果: " + immutableList);
        // 修改原集合不会影响不可变副本
        mutableList.add("Go");
        System.out.println("修改原集合后, 不可变副本仍为: " + immutableList);
    }

    /**
     * 测试 Set.copyOf() 基于已有集合创建不可变副本
     */
    @Test
    public void testSetCopyOf() {
        // 基于已有集合创建不可变 Set
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        Set<String> immutableSet = Set.copyOf(list);
        System.out.println("Set.copyOf() 创建的结果: " + immutableSet);
    }

    /**
     * 测试 Map.copyOf() 基于已有 Map 创建不可变副本
     */
    @Test
    public void testMapCopyOf() {
        Map<String, Integer> mutableMap = new java.util.HashMap<>();
        mutableMap.put("Java", 1);
        mutableMap.put("Python", 2);
        Map<String, Integer> immutableMap = Map.copyOf(mutableMap);
        System.out.println("Map.copyOf() 创建的结果: " + immutableMap);
    }

    /**
     * 测试向不可变 List 添加元素会抛出 UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testModifyImmutableListThrowsException() {
        // 向不可变 List 添加元素, 抛出 UnsupportedOperationException
        List<String> list = List.of("Java", "Python", "Go");
        list.add("C++");
    }

    /**
     * 测试删除不可变 List 元素会抛出 UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveImmutableListThrowsException() {
        // 删除不可变 List 中的元素, 抛出 UnsupportedOperationException
        List<String> list = List.of("Java", "Python", "Go");
        list.remove("Java");
    }

    /**
     * 测试向不可变 Map 添加键值对会抛出 UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testModifyImmutableMapThrowsException() {
        // 向不可变 Map 添加键值对, 抛出 UnsupportedOperationException
        Map<String, Integer> map = Map.of("Java", 1);
        map.put("Python", 2);
    }

    /**
     * 测试不可变集合不允许包含 null 元素, 否则抛出 NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testNullElementThrowsException() {
        // 向 List.of() 中传入 null 元素, 抛出 NullPointerException
        List.of("Java", null);
    }

    /**
     * 测试 Set.of() 存在重复元素会抛出 IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateElementThrowsException() {
        // Set 不允许重复元素, 传入重复元素抛出 IllegalArgumentException
        Set.of("Java", "Java");
    }

    /**
     * 测试 Map.of() 存在重复键会抛出 IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateKeyThrowsException() {
        // Map 不允许重复键, 传入重复键抛出 IllegalArgumentException
        Map.of("Java", 1, "Java", 2);
    }
}
