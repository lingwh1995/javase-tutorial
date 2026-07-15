package org.bluebridge.java8.chapter_01_interface.chapter_03_custom_functional_interface;

/**
 * 三参有返回值的函数式接口
 *
 * @author lingwh
 * @date 2025/12/2 15:41
 */
@FunctionalInterface
public interface MyFunctionInterface_3 {

    int op(int a, int b, int c);
}
