package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 厚底披萨
 * @date 2026/7/9 00:00
 */
public class ThickcrustPizza extends Pizza {

    public ThickcrustPizza() {
        description = "Thick crust pizza, with tomato sauce";
    }

    public double cost() {
        return 7.99;
    }
}
