package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 橄榄配料
 * @date 2026/7/9 00:00
 */
public class Olives extends ToppingDecorator {

    public Olives(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    public double cost() {
        return pizza.cost() + .30;
    }
}
