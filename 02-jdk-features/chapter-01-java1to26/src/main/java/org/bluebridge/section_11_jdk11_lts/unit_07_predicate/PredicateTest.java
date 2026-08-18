package org.bluebridge.section_11_jdk11_lts.unit_07_predicate;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * JDK 11 Predicate 新增方法测试
 *
 * JDK 11 为 Predicate 新增了 not() 静态方法, 用于对 Predicate 取反,
 * 常与 filter() 方法结合使用, 使代码更加简洁易读:
 * 1. Predicate.not(): 返回一个对指定 Predicate 取反的 Predicate
 * 2. 结合 Stream.filter() 使用, 替代繁琐的 lambda 取反写法
 *
 * @author lingwh
 * @date 2026/08/18 11:45
 */
public class PredicateTest {

    /**
     * 测试 Predicate.not() 基本用法: 对 Predicate 取反
     */
    @Test
    public void testPredicateNot() {
        // 定义一个判断字符串是否为空的 Predicate
        Predicate<String> isEmpty = String::isEmpty;
        // 使用 Predicate.not() 对 isEmpty 取反
        Predicate<String> isNotEmpty = Predicate.not(isEmpty);

        System.out.println("isEmpty.test(\"\"): " + isEmpty.test(""));
        System.out.println("isNotEmpty.test(\"\"): " + isNotEmpty.test(""));
        System.out.println("isEmpty.test(\"hello\"): " + isEmpty.test("hello"));
        System.out.println("isNotEmpty.test(\"hello\"): " + isNotEmpty.test("hello"));
    }

    /**
     * 测试 Predicate.not() 结合 Stream.filter() 使用
     */
    @Test
    public void testPredicateNotWithFilter() {
        List<String> list = Arrays.asList("java", "", "python", "", "go", "  ");

        // JDK 11 之前: 使用 lambda 取反, 需要显式写 !
        List<String> beforeJdk11 = list.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println("JDK 11 之前过滤空白字符串: " + beforeJdk11);

        // JDK 11 之后: 使用 Predicate.not() 取反, 语义更清晰
        List<String> afterJdk11 = list.stream()
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        System.out.println("JDK 11 之后过滤空白字符串: " + afterJdk11);
    }

    /**
     * 测试 Predicate.not() 与方法引用结合的常见场景
     */
    @Test
    public void testPredicateNotWithMethodReference() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, null, 8, null, 10);

        // 使用 Predicate.not() 过滤掉 null 值
        List<Integer> nonNullNumbers = numbers.stream()
                .filter(Predicate.not(Objects::isNull))
                .collect(Collectors.toList());
        System.out.println("过滤 null 后的列表: " + nonNullNumbers);

        // 过滤出偶数
        List<Integer> evenNumbers = numbers.stream()
                .filter(Predicate.not(Objects::isNull))
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("过滤 null 后的偶数: " + evenNumbers);
    }

    /**
     * 测试 Predicate.not() 在复杂条件中的应用
     */
    @Test
    public void testPredicateNotWithComplexCondition() {
        List<String> words = Arrays.asList("hello", "world", "java", "python", "go", "rust");

        // 定义一个判断字符串长度是否大于 3 的 Predicate
        Predicate<String> lengthGreaterThan3 = s -> s.length() > 3;

        // 使用 Predicate.not() 取反, 过滤出长度小于等于 3 的字符串
        List<String> shortWords = words.stream()
                .filter(Predicate.not(lengthGreaterThan3))
                .collect(Collectors.toList());
        System.out.println("长度 <= 3 的单词: " + shortWords);

        // 结合多个 Predicate 使用
        Predicate<String> startsWithJ = s -> s.startsWith("j");
        List<String> notStartWithJAndLong = words.stream()
                .filter(Predicate.not(startsWithJ))
                .filter(lengthGreaterThan3)
                .collect(Collectors.toList());
        System.out.println("不以 j 开头且长度 > 3 的单词: " + notStartWithJAndLong);
    }

    /**
     * 测试 Predicate.not() 与 isEqual() 结合使用
     */
    @Test
    public void testPredicateNotWithIsEqual() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Bob", "David");

        // 使用 Predicate.not(Predicate.isEqual("Bob")) 过滤掉指定值
        List<String> filteredNames = names.stream()
                .filter(Predicate.not(Predicate.isEqual("Bob")))
                .collect(Collectors.toList());
        System.out.println("过滤掉 Bob 后的名字列表: " + filteredNames);
    }
}
