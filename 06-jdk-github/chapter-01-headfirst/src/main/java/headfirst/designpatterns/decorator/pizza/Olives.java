package headfirst.designpatterns.decorator.pizza;

/**
 * 橄榄配料
 *
 * @author lingwh
 * @date 2023/12/7 19:25
 */
public class Olives extends ToppingDecorator {

    public Olives(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double cost() {
        return pizza.cost() + .30;
    }
}
