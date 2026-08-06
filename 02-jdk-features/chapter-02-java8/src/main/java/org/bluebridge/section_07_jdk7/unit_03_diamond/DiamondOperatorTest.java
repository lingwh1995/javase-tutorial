package org.bluebridge.section_07_jdk7.unit_03_diamond;

import org.junit.Test;

import java.util.*;

/**
 * JDK 7 引入的菱形操作符测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class DiamondOperatorTest {

    /**
     * 测试在 List 中使用菱形操作符
     */
    @Test
    public void testDiamondWithList() {
        // JDK 7 之前：需要在构造器中也显式指定泛型类型
        // List<String> list = new ArrayList<String>();
        // JDK 7 引入菱形操作符 <>，编译器会根据左侧声明自动推断类型
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        System.out.println("菱形操作符创建 List: " + list);
    }

    /**
     * 测试在 Map 中使用菱形操作符
     */
    @Test
    public void testDiamondWithMap() {
        // 使用菱形操作符创建 Map
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 90);
        map.put("数学", 85);
        map.put("英语", 92);
        System.out.println("菱形操作符创建 Map: " + map);

        // 使用菱形操作符创建嵌套泛型
        Map<String, List<Integer>> scoreMap = new HashMap<>();
        scoreMap.put("张三", Arrays.asList(90, 85, 92));
        scoreMap.put("李四", Arrays.asList(88, 76, 95));
        System.out.println("菱形操作符创建嵌套泛型 Map: " + scoreMap);
    }

    /**
     * 测试在 Set 中使用菱形操作符
     */
    @Test
    public void testDiamondWithSet() {
        // 使用菱形操作符创建 Set
        Set<String> set = new HashSet<>();
        set.add("苹果");
        set.add("香蕉");
        set.add("橘子");
        System.out.println("菱形操作符创建 Set: " + set);
    }

    /**
     * 测试在匿名内部类中使用菱形操作符
     * 注意：JDK 9 之前，匿名内部类中不能使用菱形操作符
     * 这里展示的是 JDK 7 中无法通过编译的写法，供对比参考
     */
    @Test
    public void testDiamondWithAnonymousClass() {
        // 传统写法：匿名内部类中必须显式指定泛型类型
        List<String> list = new ArrayList<String>() {
            {
                add("初始化值1");
                add("初始化值2");
            }
        };
        System.out.println("匿名内部类传统写法: " + list);

        // 注意：在 JDK 7 中，以下写法无法通过编译
        // List<String> list2 = new ArrayList<>() { ... };
        // 这是 JDK 9 才支持的语法改进
    }

    /**
     * 测试对比传统写法（JDK 7 之前的写法）
     */
    @Test
    public void testTraditionalDiamond() {
        // JDK 7 之前的传统写法，需要在两边都写泛型类型
        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Set<Double> set = new HashSet<Double>();

        list.add("传统写法");
        map.put("key", 1);
        set.add(3.14);

        System.out.println("传统写法 List: " + list);
        System.out.println("传统写法 Map: " + map);
        System.out.println("传统写法 Set: " + set);
    }
}