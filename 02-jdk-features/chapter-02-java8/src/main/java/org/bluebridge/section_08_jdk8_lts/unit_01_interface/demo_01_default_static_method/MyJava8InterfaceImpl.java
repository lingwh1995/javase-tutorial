package org.bluebridge.section_08_jdk8_lts.unit_01_interface.demo_01_default_static_method;

/**
 * Java8 接口的实现类
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
