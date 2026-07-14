package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 低咖啡因咖啡
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf Coffee";
    }

    public double cost() {
        return 1.05;
    }
}
