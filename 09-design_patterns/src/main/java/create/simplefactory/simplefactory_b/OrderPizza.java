package create.simplefactory.simplefactory_b;

/**
 * @author lingwh
 * @desc 方法的使用者:
 * @date 2026/7/9 00:00
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
