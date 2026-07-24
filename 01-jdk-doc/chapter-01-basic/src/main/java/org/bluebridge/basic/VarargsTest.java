package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 可变参数
 *
 * 1. 只有最后一个形参才能被定义成可变参数
 * 2. 一个方法的形参列表中只能有一个可变参数
 * 3. 方法的形参数组，如：fun(int[] nums) 也可以实现可变参数的效果，但是调用时较为复杂，可变参数调用时就简单很多了
 * 4. 可变参数底层也是通过数组实现，获取该可变参数的具体类型，根据该类型创建该类型的形参数组
 * 5. 泛型不可以和可变参数配置使用，如：fun(T... args)
 *
 * @author lingwh
 * @date 2019/4/10 13:39
 */
@Slf4j
public class VarargsTest {

    /**
     * 测试 java 的可变参数
     */
    @Test
    public void testVarargs() {
        // 传递多个 String 类型参数
        testVarargs1(1, "乒乓", "篮球");

        // 传递一个 String[] 类型的参数
        testVarargs1(2, new String[] {"唱歌", "听音乐"});

        // 接收并转发可变参数
        testVarargs2(3, new String[] {"吃饭", "睡觉"});

        // 可变参数和泛型配合使用
        testVarargs3(4, "hello", 123);
        testVarargs3(4, 123, "world");
    }

    /**
     * 接收并处理可变参数
     *
     * 1. 只有最后一个形参可以作为可变参数
     * 2. 可变参数类型为：多个 String 字符串/一个 String[])
     *
     * @param age
     * @param favorites
     */
    private static void testVarargs1(int age, String... favorites) {
        log.info("#可变参数接收多个String字符串/一个String[]:");
        log.info("第一个参数: {}", age);
        log.info("可变参数的类型: {}", favorites.getClass().getSimpleName());
        StringBuilder varargsBuilder = new StringBuilder();
        for (String favorite : favorites) {
            varargsBuilder.append(favorite).append(" ");
        }
        log.info("第二个参数（可变参数）: {}", varargsBuilder);
        log.info("-------------------------------------------");
    }

    /**
     * 接收转发可变参数
     *
     * @param
     * @return void
     * @throws
     */
    private static void testVarargs2(int age, String... favorites) {
        log.info("#可变参数转发:");
        testVarargs1(age, favorites);
    }

    /**
     * 可变参数和泛型转发使用
     *
     * @param age
     * @param favorites
     * @param <T>
     */
    private static <T> void testVarargs3(int age, T... favorites) {
        log.info("#可变参数和泛型配合使用:");
        log.info("第一个参数: {}", age);
        log.info("可变参数的类型: {}", favorites.getClass().getSimpleName());
        StringBuilder varargsBuilder = new StringBuilder();
        for (T favorite : favorites) {
            varargsBuilder.append(favorite).append(" ");
        }
        log.info("第二个参数（可变参数）: {}", varargsBuilder);
        log.info("-------------------------------------------");
    }
}
