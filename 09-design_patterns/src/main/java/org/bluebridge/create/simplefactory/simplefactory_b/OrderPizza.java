package org.bluebridge.create.simplefactory.simplefactory_b;

/**
 * 方法的使用者:
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class OrderPizza {

    private Pizza pizza = null;

    public OrderPizza(String pizzType) {
        this.pizza = PizzaFacory.createPizza(pizzType);
    }

    public Pizza order() {
        return pizza;
    }
}
