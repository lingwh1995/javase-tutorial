package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 浓缩咖啡
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}
