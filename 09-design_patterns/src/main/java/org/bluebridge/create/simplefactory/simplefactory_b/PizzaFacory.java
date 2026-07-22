package org.bluebridge.create.simplefactory.simplefactory_b;

/**
 * 披萨工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PizzaFacory {

    public static Pizza createPizza(String pizzType) {
        Pizza pizza = null;
        if ("greek".equals(pizzType)) {
            pizza = new GreekPizza();
        } else if ("asian".equals(pizzType)) {
            pizza = new AsianPizza();
        } else {
            pizza = null;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
