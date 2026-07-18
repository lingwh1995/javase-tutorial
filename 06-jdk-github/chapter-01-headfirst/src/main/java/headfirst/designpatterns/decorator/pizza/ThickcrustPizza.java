package headfirst.designpatterns.decorator.pizza;

/**
 * 厚底披萨
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class ThickcrustPizza extends Pizza {

    public ThickcrustPizza() {
        description = "Thick crust pizza, with tomato sauce";
    }

    public double cost() {
        return 7.99;
    }
}
