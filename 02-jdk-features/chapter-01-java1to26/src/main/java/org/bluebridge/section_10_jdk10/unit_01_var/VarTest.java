package org.bluebridge.section_10_jdk10.unit_01_var;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java10 局部变量类型推断(var)测试
 *
 * Java10 引入 var 关键字, 允许在声明局部变量时省略类型, 由编译器根据初始化值自动推断,
 * 该特性可以简化代码编写, 提升代码可读性, 使用要点如下:
 * 1. var 只能用于局部变量, 包括普通局部变量、for 循环变量、try-with-resources 资源变量
 * 2. var 不能用于方法参数、方法返回值、成员变量(字段)等位置
 * 3. var 声明时必须初始化, 不能初始化为 null, 也不能用 lambda 表达式等无法推断类型的方式初始化
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class VarTest {

    /**
     * 测试 var 用于普通局部变量: 根据初始化值自动推断类型
     */
    @Test
    public void testVarWithLocalVariable() {
        // ===== 旧版实现方式(JDK 10 之前): 必须显式写出完整类型 =====
        // int age = 18;
        // String name = "张三";
        // ArrayList<String> list = new ArrayList<>();
        // HashMap<String, Integer> map = new HashMap<>();
        // ===== 新版实现方式(JDK 10 起): 使用 var 由编译器自动推断类型 =====
        // 基本类型推断
        var age = 18;
        // 引用类型推断
        var name = "张三";
        // 泛型类型推断
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Python");
        // 集合类型推断
        var map = new HashMap<String, Integer>();
        map.put("age", 18);
        // 打印推断后的实际类型
        System.out.println("age 的实际类型: " + ((Object) age).getClass().getName());
        System.out.println("name 的实际类型: " + name.getClass().getName());
        System.out.println("list 的实际类型: " + list.getClass().getName());
        System.out.println("map 的实际类型: " + map.getClass().getName());
        System.out.println("--------------------------------------");
        // 推断出具体类型后, 编译器会进行类型检查, 放入其他类型会编译报错
        // list.add(123);
    }

    /**
     * 测试 var 用于增强 for 循环和普通 for 循环
     */
    @Test
    public void testVarInForLoop() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        // ===== 旧版实现方式(JDK 10 之前): 必须显式写出循环变量类型 =====
        // for (String item : list) { System.out.println(item); }
        // for (int i = 0; i < list.size(); i++) { System.out.println(i + ": " + list.get(i)); }
        // ===== 新版实现方式(JDK 10 起): 使用 var 声明循环变量 =====
        // 增强 for 循环中使用 var 声明循环变量
        System.out.println("增强 for 循环: ");
        for (var item : list) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------");
        // 普通 for 循环中使用 var 声明循环变量
        System.out.println("普通 for 循环: ");
        for (var i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i));
        }
    }

    /**
     * 测试 var 用于 try-with-resources 资源变量
     */
    @Test
    public void testVarInTryWithResources() {
        // 在 try-with-resources 中使用 var 声明资源变量
        try (var inputStream = new ByteArrayInputStream("hello".getBytes())) {
            // 一次读取所有数据
            var data = inputStream.readAllBytes();
            System.out.println("读取到的内容: " + new String(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 var 结合匿名内部类使用
     */
    @Test
    public void testVarWithAnonymousClass() {
        // 用 var 声明匿名内部类对象, 可以调用其特有的方法
        var runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类执行了...");
            }
        };
        runnable.run();
        System.out.println("runnable 的实际类型: " + runnable.getClass().getName());
    }

    /**
     * 测试 var 的使用限制: 不能用于方法参数、返回值、成员变量等位置
     */
    @Test
    public void testVarLimitations() {
        // 1. var 不能用于方法参数
        // public void testMethod(var param) {}

        // 2. var 不能用于方法返回值
        // public var testMethod() { return "hello"; }

        // 3. var 不能用于成员变量(字段)
        // private var field = "hello";

        // 4. var 声明时必须初始化, 不能只声明不赋值
        // var value;

        // 5. var 不能被初始化为 null(无法推断类型)
        // var value = null;

        // 6. var 不能用于 lambda 表达式参数(Java11 才支持)
        // Comparator<String> comparator = (var a, var b) -> a.compareTo(b);

        System.out.println("var 不能用于方法参数、方法返回值、成员变量等位置, 上述代码均无法通过编译");
    }

    /**
     * 测试 var 在实际开发中的应用: 简化冗长的泛型声明
     */
    @Test
    public void testVarApplication() {
        // ===== 旧版实现方式(JDK 10 之前): 冗长的泛型类型声明 =====
        // HashMap<String, List<String>> map = new HashMap<>();
        // for (Map.Entry<String, List<String>> entry : map.entrySet()) {
        //     System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        // }
        // ===== 新版实现方式(JDK 10 起): 使用 var 简化冗长的集合类型声明 =====
        // 使用 var 简化冗长的集合类型声明
        var map = new HashMap<String, List<String>>();
        map.put("Java", List.of("JDK8", "JDK11", "JDK17"));
        // 遍历时使用 var 声明循环变量, 无需关心具体类型
        for (var entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
