package org.bluebridge.java8.chapter_01_interface.chapter_01_default_static_method;

/**
 * @author lingwh
 * @desc 定义一个Java8接口，可以包含抽象方法、静态方法、默认方法、
 * @date 2025/12/2 15:07
 */
public interface MyJava8Interface {

    /**
     * 定义一个抽象方法
     */
    void abstractMethod_1();

    /**
     * 定义一个抽象方法
     */
    void abstractMethod_2();

    /**
     * 定义一个静态方法
     */
    static void staticMethod() {
        System.out.println("我是接口中的静态方法......");
    }

    /**
     * 定义一个默认方法
     */
    default void defaultMethod() {
        System.out.println("我是接口中的默认方法......");
    }
}
