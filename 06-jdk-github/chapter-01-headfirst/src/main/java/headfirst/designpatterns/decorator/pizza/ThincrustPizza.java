package headfirst.designpatterns.decorator.pizza;

/**
 * 薄底披萨
 *
 * @author lingwh
 * @date 2023/12/7 22:29
 */
public class ThincrustPizza extends Pizza {

    public ThincrustPizza() {
        description = "Thin crust pizza, with tomato sauce";
    }

    @Override
    public double cost() {
        return 7.99;
    }
}
