package headfirst.designpatterns.factory.pizzaaf;

/**
 * @author lingwh
 * @desc 披萨店抽象类
 * @date 2026/7/9 00:00
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
