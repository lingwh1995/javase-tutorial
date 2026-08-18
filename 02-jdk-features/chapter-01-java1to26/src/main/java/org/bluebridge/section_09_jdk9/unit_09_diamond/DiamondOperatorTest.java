package org.bluebridge.section_09_jdk9.unit_09_diamond;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Java9 Diamond 操作符扩展测试
 *
 * Java9 对钻石操作符(<> )进行了扩展, 允许在匿名内部类中使用:
 * 1. Java7 引入了钻石操作符, 但不能在匿名内部类中使用
 * 2. Java9 允许在匿名内部类中使用 <> 进行类型推断, 简化了代码
 * 3. 编译器会根据上下文自动推断类型参数
 *
 * 演化历程: 钻石操作符扩展 JDK 9 STANDARD（JEP 213），JDK 7 引入，JDK 9 扩展匿名类
 *
 * @author lingwh
 * @date 2026/08/06 14:07
 */
public class DiamondOperatorTest {

    /**
     * 测试 Java9 钻石操作符: 在匿名内部类中使用 <>
     * 创建匿名内部类时, 使用 <> 让编译器自动推断类型
     */
    @Test
    public void testDiamondInAnonymousClass() {
        // Java9 之前: 匿名内部类中必须显式指定类型参数
        // Java9 开始: 可以使用 <> 让编译器自动推断
        List<String> list = new ArrayList<>() {
            // 匿名内部类中可以添加自定义行为
            @Override
            public boolean add(String s) {
                System.out.println("添加元素: " + s);
                return super.add(s);
            }
        };
        list.add("Java");
        list.add("Python");
        System.out.println("列表内容: " + list);
    }

    /**
     * 测试 Java9 钻石操作符: 在匿名内部类中实现接口
     */
    @Test
    public void testDiamondInAnonymousInterface() {
        // 使用钻石操作符创建匿名内部类实现 Comparator 接口
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                // 按字符串长度降序排列
                return Integer.compare(s2.length(), s1.length());
            }
        };
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        list.sort(comparator);
        System.out.println("按长度降序排列: " + list);
    }

    /**
     * 测试 Java9 钻石操作符: 在匿名内部类中继承泛型父类
     */
    @Test
    public void testDiamondInGenericInheritance() {
        // 使用钻石操作符创建匿名内部类, 继承泛型父类
        Processor<String> processor = new Processor<>() {
            @Override
            public String process(String input) {
                return "处理结果: " + input.toUpperCase();
            }
        };
        String result = processor.process("hello");
        System.out.println(result);
    }

    /**
     * 测试对比: Java9 钻石操作符 vs 传统方式
     */
    @Test
    public void testComparisonWithTraditionalWay() {
        // 传统方式(Java7 之前): 匿名内部类必须显式指定类型参数
        List<String> traditional = new ArrayList<String>() {
            @Override
            public boolean add(String s) {
                return super.add(s);
            }
        };
        // Java9 方式: 匿名内部类中使用钻石操作符
        List<String> modern = new ArrayList<>() {
            @Override
            public boolean add(String s) {
                return super.add(s);
            }
        };
        traditional.add("传统方式");
        modern.add("现代方式");
        System.out.println("传统方式列表: " + traditional);
        System.out.println("现代方式列表: " + modern);
    }

    /**
     * 测试 Java9 钻石操作符: 多层泛型嵌套的匿名内部类
     */
    @Test
    public void testDiamondWithNestedGenerics() {
        // 创建嵌套泛型 List<List<String>> 的匿名内部类
        List<List<String>> nestedList = new ArrayList<>() {
            @Override
            public boolean add(List<String> strings) {
                System.out.println("添加嵌套列表: " + strings);
                return super.add(strings);
            }
        };
        nestedList.add(List.of("Java", "Python"));
        nestedList.add(List.of("Go", "Rust"));
        System.out.println("嵌套列表内容: " + nestedList);
    }

    /**
     * 泛型处理接口, 用于演示匿名内部类继承
     */
    private interface Processor<T> {
        T process(T input);
    }
}