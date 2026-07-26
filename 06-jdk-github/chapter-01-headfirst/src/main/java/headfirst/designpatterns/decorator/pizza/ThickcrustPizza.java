package headfirst.designpatterns.decorator.pizza;

/**
 * 厚底披萨
 *
 * @author lingwh
 * @date 2023/12/7 21:13
 */
public class ThickcrustPizza extends Pizza {

    public ThickcrustPizza() {
        description = "Thick crust pizza, with tomato sauce";
    }

    @Override
    public double cost() {
        return 7.99;
    }
}
