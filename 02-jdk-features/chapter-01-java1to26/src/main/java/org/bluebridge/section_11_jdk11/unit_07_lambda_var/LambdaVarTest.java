package org.bluebridge.section_11_jdk11.unit_07_lambda_var;

import lombok.NonNull;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java11 Lambda 局部变量语法测试
 *
 * Java11 允许在 Lambda 表达式中使用 var 关键字声明参数, 使参数可以像普通局部变量一样
 * 使用修饰符(如 @NonNull, @Nullable 等注解), 语法规则如下:
 * 1. (var x, var y) -> x + y: 使用 var 声明 Lambda 参数类型
 * 2. (@NonNull var x) -> x.toString(): 在 var 参数上使用注解
 * 3. 所有参数必须同时使用 var 或都不使用, 不能混用
 *
 * @author lingwh
 * @date 2026/08/06 14:07
 */
public class LambdaVarTest {

    /**
     * 测试 Lambda 中使用 var 声明参数的基本语法
     */
    @Test
    public void testLambdaVarBasic() {
        // 使用 var 声明 Lambda 参数
        // 等价于 (Integer x, Integer y) -> x + y
        MathOperation addition = (var x, var y) -> x + y;
        System.out.println("(var x, var y) -> x + y, 结果: " + addition.operate(10, 20));

        // 使用 var 声明单个参数的 Lambda
        StringConverter toUpperCase = (var s) -> s.toUpperCase();
        System.out.println("(var s) -> s.toUpperCase(), 结果: " + toUpperCase.convert("hello"));
    }

    /**
     * 测试 var 在 Lambda 中的类型推断: 编译器会根据上下文推断 var 的具体类型
     */
    @Test
    public void testLambdaVarTypeInference() {
        // 两个字符串参数拼接
        MathOperation<String> stringConcat = (var x, var y) -> x + y;
        System.out.println("字符串拼接: " + stringConcat.operate("hello ", "world"));

        // 两个整数参数相加
        MathOperation<Integer> integerAdd = (var x, var y) -> x + y;
        System.out.println("整数相加: " + integerAdd.operate(100, 200));
    }

    /**
     * 测试 var 在 Lambda 中与注解结合使用
     */
    @Test
    public void testLambdaVarWithAnnotation() {
        // 使用 @NonNull 注解标记 Lambda 参数, 表示参数不能为 null
        StringConverter nonNullConverter = (@NonNull var s) -> s.toUpperCase();
        System.out.println("(@NonNull var s) -> s.toUpperCase(), 结果: " + nonNullConverter.convert("annotation"));
    }

    /**
     * 测试 var 在 Lambda 中与 Stream 结合使用
     */
    @Test
    public void testLambdaVarWithStream() {
        List<String> list = Arrays.asList("java", "python", "go", "rust", "c++");

        // 在 Stream 的 filter 和 map 中使用 var 声明 Lambda 参数
        List<String> result = list.stream()
                .filter((var s) -> s.length() > 3)
                .map((var s) -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("过滤并转换后的结果: " + result);
    }

    /**
     * 测试 var 与隐式类型 Lambda 的对比
     */
    @Test
    public void testLambdaVarComparison() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        // 隐式类型(不使用 var): 最简洁的写法
        List<String> implicitType = words.stream()
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println("隐式类型: " + implicitType);

        // 显式类型(使用 var): 当需要添加注解时使用
        List<String> varType = words.stream()
                .filter((@NonNull var s) -> s.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println("var 类型(带注解): " + varType);
    }

    /**
     * 数学运算接口
     */
    @FunctionalInterface
    interface MathOperation<T extends Number> {
        T operate(T x, T y);
    }

    /**
     * 字符串转换接口
     */
    @FunctionalInterface
    interface StringConverter {
        String convert(String s);
    }
}