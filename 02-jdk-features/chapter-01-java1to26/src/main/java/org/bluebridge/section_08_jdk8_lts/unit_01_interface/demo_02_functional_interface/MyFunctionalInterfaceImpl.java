package org.bluebridge.section_08_jdk8_lts.unit_01_interface.demo_02_functional_interface;

/**
 * 实现函数式接口中的抽象方法
 *
 * @author lingwh
 * @date 2025/12/2 15:22
 */
public class MyFunctionalInterfaceImpl implements MyFunctionalInterface {

    @Override
    public void abstractMethod() {
        System.out.println("我是实现类中的抽象方法......");
    }
}
