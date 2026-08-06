package org.bluebridge.section_26_jdk26.unit_04_module_import;

import org.junit.Test;

/**
 * JDK 26 模块导入声明测试(STANDARD 正式特性)
 *
 * 模块导入声明(Module Import Declarations, JEP 476) 在 JDK 26
 * 中转正为 STANDARD 正式特性。该特性允许使用 {@code import module M;}
 * 语法一次性导入模块 M 导出的所有包中的公开顶级类和接口。
 *
 * 例如: import module java.base;
 * 等效于: import java.lang.*; import java.util.*; import java.io.*;
 *         import java.nio.file.*; 等 java.base 模块导出的所有包
 *
 * 注意: 本文件中的 import module 语法为 JDK 26 STANDARD 特性,
 *       但需要模块化环境的支持。
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
// 使用模块导入声明, 导入 java.base 模块导出的所有包
// 这样可以直接使用 List, Map, Stream, Path 等类而无需逐个导入
import module java.base;

/**
 * 测试模块导入声明的使用
 * 通过 import module java.base 导入 java.base 模块的所有导出包,
 * 可以直接使用 java.util.List, java.util.Map, java.util.stream.Stream,
 * java.nio.file.Path 等类
 */
public class ModuleImportTest {

    /**
     * 测试模块导入后直接使用 java.base 模块中的类(STANDARD)
     * 通过 import module java.base, 无需额外导入即可使用
     * List, Map, Stream, Path 等来自 java.base 模块的类
     */
    @Test
    public void testModuleImportBasic() {
        // 直接使用 List (来自 java.util)
        List<String> fruits = List.of("苹果", "香蕉", "橙子");
        System.out.println("水果列表: " + fruits);
        System.out.println("--------------------------------------");

        // 直接使用 Map (来自 java.util)
        Map<String, Integer> scores = Map.of("张三", 95, "李四", 88, "王五", 76);
        System.out.println("成绩表: " + scores);
        System.out.println("--------------------------------------");

        // 直接使用 Stream (来自 java.util.stream)
        Stream<String> stream = fruits.stream();
        stream.map(s -> "水果: " + s).forEach(System.out::println);
    }

    /**
     * 测试模块导入后使用 java.time 和 java.nio.file 中的类(STANDARD)
     * java.base 模块导出了 java.time, java.nio.file 等包,
     * 模块导入后可以直接使用这些包中的类
     */
    @Test
    public void testModuleImportDateTimeAndFile() {
        // 直接使用 LocalDate (来自 java.time)
        LocalDate today = LocalDate.now();
        System.out.println("当前日期: " + today);
        System.out.println("--------------------------------------");

        // 直接使用 Path (来自 java.nio.file)
        Path currentPath = Paths.get(".");
        System.out.println("当前路径: " + currentPath.toAbsolutePath().normalize());
        System.out.println("--------------------------------------");

        // 直接使用 Files (来自 java.nio.file)
        System.out.println("当前目录内容:");
        try (Stream<Path> paths = Files.list(currentPath)) {
            paths.limit(5).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("列出目录失败: " + e.getMessage());
        }
    }

    /**
     * 测试模块导入后使用集合工具类(STANDARD)
     * 通过模块导入, 可以直接使用 Collections, Arrays 等工具类
     */
    @Test
    public void testModuleImportCollections() {
        // 直接使用 ArrayList (来自 java.util)
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        System.out.println("编程语言列表: " + list);
        System.out.println("--------------------------------------");

        // 直接使用 Collections (来自 java.util)
        Collections.sort(list);
        System.out.println("排序后: " + list);
        System.out.println("--------------------------------------");

        // 直接使用 Arrays (来自 java.util)
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(numbers);
        System.out.println("排序后的数组: " + Arrays.toString(numbers));
    }

    /**
     * 测试模块导入后使用函数式接口和 Stream 操作(STANDARD)
     * 模块导入后可以直接使用 Function, Predicate, Collectors 等类
     */
    @Test
    public void testModuleImportStreamOperations() {
        // 直接使用 Stream, Collectors, Function (来自 java.util.stream 和 java.util.function)
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // 使用 Stream API 进行复杂操作
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("按名字长度分组:");
        groupedByLength.forEach((length, nameList) ->
                System.out.println("  长度 " + length + ": " + nameList));
        System.out.println("--------------------------------------");

        // 使用 Function 和 Predicate
        List<String> filtered = names.stream()
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("长度大于3的大写名字: " + filtered);
    }
}