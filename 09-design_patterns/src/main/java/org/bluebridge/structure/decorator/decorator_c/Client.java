package org.bluebridge.structure.decorator.decorator_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 10:12
 */
public class Client {

    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("---------------------------------");
        Component d = new ConcreteDecorator(p);
        d.operation();
    }
}
