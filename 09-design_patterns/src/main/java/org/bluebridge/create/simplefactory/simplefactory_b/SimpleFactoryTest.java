package org.bluebridge.create.simplefactory.simplefactory_b;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 19:08
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        OrderPizza greekPizza = new OrderPizza("greek");
        Pizza greek = greekPizza.order();
        OrderPizza asianPizza = new OrderPizza("asian");
        Pizza asian = asianPizza.order();
    }
}
