package org.bluebridge.java8.chapter_01_interface.chapter_02_functional_interface;

/**
 * @author lingwh
 * @desc
 * @date 2025/12/2 15:25
 */
public class MyFunctionalInterfaceTest {

     public static void main(String[] args) {
        // 创建实现类对象
        MyFunctionalInterfaceImpl myFunctionalInterfaceImpl = new MyFunctionalInterfaceImpl();

        // 调用实现类中的抽象方法
        myFunctionalInterfaceImpl.abstractMethod();

        // 调用接口中的默认方法
        myFunctionalInterfaceImpl.defaultMethod();

        // 调用接口中的静态方法
        MyFunctionalInterface.staticMethod();
    }

}
