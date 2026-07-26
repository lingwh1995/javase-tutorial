package org.bluebridge.create.abstractfactory.abstractfactory_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/9/4 10:31
 */
public class Client {

    public static void main(String[] args) {
        // 创建抽象工厂对象
        AbstractFactory af = new ConcreteFactory1();
        // 通过抽象工厂来获取一系列的对象，如产品 A 和产品 B
        af.createProductA();
        af.createProductB();
    }
}
