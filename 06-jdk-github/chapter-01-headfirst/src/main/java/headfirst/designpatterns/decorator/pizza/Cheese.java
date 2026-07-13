package headfirst.designpatterns.decorator.pizza;

/**
 * 芝士配料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Cheese extends ToppingDecorator {

    public Cheese(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    public double cost() {
        return pizza.cost(); // cheese is free
    }
}
