package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 薄底披萨
 * @date 2026/7/9 00:00
 */
public class ThincrustPizza extends Pizza {

    public ThincrustPizza() {
        description = "Thin crust pizza, with tomato sauce";
    }

    public double cost() {
        return 7.99;
    }
}
