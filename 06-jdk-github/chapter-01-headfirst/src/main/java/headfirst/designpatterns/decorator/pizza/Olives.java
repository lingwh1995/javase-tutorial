package headfirst.designpatterns.decorator.pizza;

/**
 * 橄榄配料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
