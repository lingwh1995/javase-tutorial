package org.bluebridge.java8.section_01_interface.unit_01_default_static_method;

/**
 * 定义一个 Java8 接口，可以包含抽象方法、静态方法、默认方法、
 *
 * @author lingwh
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
