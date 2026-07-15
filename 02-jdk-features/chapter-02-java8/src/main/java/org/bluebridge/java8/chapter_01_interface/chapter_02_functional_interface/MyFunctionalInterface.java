package org.bluebridge.java8.chapter_01_interface.chapter_02_functional_interface;

/**
 * 函数式接口是指只包含一个抽象方法的接口，注意：这意味着除了一个抽象方法外，接口还可以定义多个静态方法或多个默认方法。
 *
 * @author lingwh
 * @date 2025/12/2 15:18
 */
@FunctionalInterface
public interface MyFunctionalInterface {

    /**
     * 定义一个抽象方法
     */
    void abstractMethod();

    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.out.println("我是接口中的默认方法......");
    }

    /**
     * 静态方法
     */
    static void staticMethod() {
        System.out.println("我是接口中的静态方法......");
    }
}
