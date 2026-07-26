package headfirst.designpatterns.decorator.pizza;

/**
 * 芝士配料
 *
 * @author lingwh
 * @date 2023/12/7 18:07
 */
public class Cheese extends ToppingDecorator {

    public Cheese(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    public double cost() {
        return pizza.cost(); // cheese is free
    }
}
