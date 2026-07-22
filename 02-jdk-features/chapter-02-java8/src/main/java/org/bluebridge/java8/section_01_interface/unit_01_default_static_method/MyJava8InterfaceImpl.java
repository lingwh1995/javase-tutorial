package org.bluebridge.java8.section_01_interface.unit_01_default_static_method;

/**
 * Java8接口的实现类
 *
 * @author lingwh
 * @date 2025/12/2 15:10
 */
public class MyJava8InterfaceImpl implements MyJava8Interface {

    @Override
    public void abstractMethod_1() {
        System.out.println("我是实现类中的抽象方法1......");
    }

    @Override
    public void abstractMethod_2() {
        System.out.println("我是实现类中的抽象方法2......");
    }
}
