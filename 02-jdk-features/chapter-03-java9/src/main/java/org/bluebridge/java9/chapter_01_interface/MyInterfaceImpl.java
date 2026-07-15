package org.bluebridge.java9.chapter_01_interface;

/**
 * MyInterface的实现类
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class MyInterfaceImpl implements MyInterface {

    @Override
    public void methodAbstract() {
        System.out.println("我是实现类中的抽象方法...");
    }

    @Override
    public void methodDefault() {
        System.out.println("我是实现类中的默认方法...");
    }
}
