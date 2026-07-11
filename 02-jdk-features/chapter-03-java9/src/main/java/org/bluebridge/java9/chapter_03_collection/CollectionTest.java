package org.bluebridge.java9.chapter_03_collection;

import java.util.*;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Java9只读集合创建测试
 * @date 2026/7/9 00:00
 */
public class CollectionTest {

    /**
     * Java9之前创建只读集合
     */
    @Test
    public void testCreateUnmodifiableCollectionBeforeJava9() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        // 设为只读List集合
        list = Collections.unmodifiableList(list);
        System.out.println(list);
        System.out.println("--------------------------------------");

        Set<String> set = new HashSet<>();
        set.add("E");
        set.add("F");
        set.add("G");
        // 设为只读Set集合
        set = Collections.unmodifiableSet(set);
        System.out.println(set);
        System.out.println("--------------------------------------");

        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        // 设为只读Map集合
        map = Collections.unmodifiableMap(map);
        System.out.println(map);
        System.out.println("--------------------------------------");

        // 创建普通集合
        List<String> unmodifiableList = Arrays.asList("1", "2", "3");
        // 调用后会报 java.lang.UnsupportedOperationException
        unmodifiableList.add("A");
        System.out.println("--------------------------------------");
    }

    /**
     * Java9之后创建只读集合
     *     java9提供了更方便的方式来创建只读集合
     */
    @Test
    public void testCreateUnmodifiableCollectionAfterJava9() {
        // 创建只读List集合
        List<String> list = List.of("A", "B", "C");
        System.out.println(list);
        System.out.println("--------------------------------------");

        // 创建只读Set集合
        Set<String> set = Set.of("E", "F", "G");
        System.out.println(set);
        System.out.println("--------------------------------------");

        // 创建只读Map集合
        Map<String, String> map = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
        System.out.println(map);
        System.out.println("--------------------------------------");

        map = Map.ofEntries(Map.entry("k1", "v1"), Map.entry("k2", "v2"), Map.entry("k3", "v3"));
        System.out.println(map);
        System.out.println("--------------------------------------");
    }
}
