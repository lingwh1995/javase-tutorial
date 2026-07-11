package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 低咖啡因咖啡
 * @date 2026/7/9 00:00
 */
public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf Coffee";
    }

    public double cost() {
        return 1.05;
    }
}
