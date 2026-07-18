package headfirst.designpatterns.decorator.pizza;

/**
 * 薄底披萨
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThincrustPizza extends Pizza {

    public ThincrustPizza() {
        description = "Thin crust pizza, with tomato sauce";
    }

    public double cost() {
        return 7.99;
    }
}
