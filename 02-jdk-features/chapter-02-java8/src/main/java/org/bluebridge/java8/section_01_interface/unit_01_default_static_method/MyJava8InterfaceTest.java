package org.bluebridge.java8.section_01_interface.unit_01_default_static_method;

/**
 * Java8接口测试
 *
 * @author lingwh
 * @date 2025/12/2 15:15
 */
public class MyJava8InterfaceTest {

    public static void main(String[] args) {
        // 直接调用接口中的静态方法
        MyJava8Interface.staticMethod();
        // 创建实现类对象
        MyJava8Interface myJava8Interface = new MyJava8InterfaceImpl();
        // 调用接口中的默认方法
        myJava8Interface.defaultMethod();
        // 调用接口中的抽象方法
        myJava8Interface.abstractMethod_1();
        myJava8Interface.abstractMethod_2();
    }
}
