package org.bluebridge.section_11_jdk11_lts.unit_05_lambda;

import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * JDK 11 Lambda 局部变量语法测试
 * Lambda 表达式中允许使用 var 声明参数，从而可以为参数添加注解
 *
 * @author lingwh
 * @date 2026/08/06 09:17
 */
public class LambdaTest {

    /**
     * 测试 Lambda 表达式中使用 var 参数
     * (var x, var y) -> x + y 语法
     */
    @Test
    public void testVarInLambda() {
        // 使用 var 声明 Lambda 参数
        BiFunction<Integer, Integer, Integer> add = (var x, var y) -> x + y;
        Integer result = add.apply(10, 20);
        System.out.println("(var x, var y) -> x + y 结果: " + result);
    }

    /**
     * 测试 Lambda 中使用 var 进行字符串拼接
     */
    @Test
    public void testVarInLambdaWithString() {
        // var 参数类型会被推断为 String
        BiFunction<String, String, String> concat = (var a, var b) -> a + " " + b;
        String result = concat.apply("Hello", "JDK 11");
        System.out.println("字符串拼接结果: " + result);
    }

    /**
     * 测试 Lambda 中使用 var 结合 Stream API
     */
    @Test
    public void testVarInLambdaWithStream() {
        List<String> list = List.of("Java", "JDK", "11", "LTS");

        // 在 Stream 的 map 操作中使用 var
        List<String> result = list.stream()
                .map((var s) -> s.toLowerCase())
                .collect(Collectors.toList());
        System.out.println("Stream map 中使用 var 结果: " + result);
    }

    /**
     * 测试 Lambda 中使用 var 进行数值计算
     */
    @Test
    public void testVarInLambdaWithCalculation() {
        // 多个参数使用 var
        BiFunction<Double, Double, Double> power = (var base, var exp) -> Math.pow(base, exp);
        Double result = power.apply(2.0, 10.0);
        System.out.println("2 的 10 次方: " + result);
    }

    /**
     * 测试 Lambda 中 var 与普通参数混合使用的限制
     * 注意：Lambda 中要么全部使用 var，要么全部不使用，不能混合使用
     */
    @Test
    public void testVarInLambdaRule() {
        // 全部使用 var（正确用法）
        BiFunction<Integer, Integer, Integer> allVar = (var a, var b) -> a * b;
        System.out.println("全部使用 var 结果: " + allVar.apply(5, 6));

        // 全部不使用 var（正确用法）
        BiFunction<Integer, Integer, Integer> noVar = (a, b) -> a * b;
        System.out.println("全部不使用 var 结果: " + noVar.apply(5, 6));

        // 注意：不能混合使用，如 (var a, b) -> a * b 会导致编译错误
        System.out.println("Lambda 中 var 使用规则：要么全部使用，要么全部不使用");
    }
}