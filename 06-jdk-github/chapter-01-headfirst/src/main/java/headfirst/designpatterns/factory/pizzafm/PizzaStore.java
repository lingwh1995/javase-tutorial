package headfirst.designpatterns.factory.pizzafm;

/**
 * 披萨店抽象类
 *
 * @author lingwh
 * @date 2023/12/7 14:11
 */
public abstract class PizzaStore {

    abstract Pizza createPizza(String item);

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
