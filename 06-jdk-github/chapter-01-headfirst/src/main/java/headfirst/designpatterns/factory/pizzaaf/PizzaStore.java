package headfirst.designpatterns.factory.pizzaaf;

/**
 * 披萨店抽象类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class PizzaStore {

    protected abstract Pizza createPizza(String item);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("--- Making a " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
