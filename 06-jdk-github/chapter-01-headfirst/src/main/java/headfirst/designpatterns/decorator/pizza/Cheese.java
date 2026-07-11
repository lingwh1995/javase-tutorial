package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 芝士配料
 * @date 2026/7/9 00:00
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
