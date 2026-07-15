package org.bluebridge.java9.chapter_01_interface;

/**
 * Java9接口方法示例
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public interface MyInterface {

    /**
     * 接口中的抽象方法
     */
    void methodAbstract();

    /**
     * 接口中的静态方法
     */
    static void methodStatic() {
        System.out.println("我是接口中的静态方法...");
    }

    /**
     * 接口中的默认方法
     */
    default void methodDefault() {
        System.out.println("我是接口中的默认方法...");
    }

    /**
     * 接口中的私有方法
     */
    private void methodPrivate() {
        System.out.println("我是接口中的私有方法...");
    }
}
