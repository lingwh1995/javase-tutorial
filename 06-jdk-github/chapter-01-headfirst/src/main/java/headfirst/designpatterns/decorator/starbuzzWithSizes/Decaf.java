package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 低咖啡因咖啡
 *
 * @author lingwh
 * @date 2023/12/7 08:13
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf Coffee";
    }

    public double cost() {
        return 1.05;
    }
}
