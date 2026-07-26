package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 浓缩咖啡
 *
 * @author lingwh
 * @date 2023/12/7 09:46
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}
