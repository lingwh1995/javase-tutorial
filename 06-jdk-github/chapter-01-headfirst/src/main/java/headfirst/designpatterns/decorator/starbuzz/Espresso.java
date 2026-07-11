package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 浓缩咖啡
 * @date 2026/7/9 00:00
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}
