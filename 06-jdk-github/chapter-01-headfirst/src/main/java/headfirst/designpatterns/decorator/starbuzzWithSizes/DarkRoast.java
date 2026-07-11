package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * @author lingwh
 * @desc 深焙咖啡
 * @date 2026/7/9 00:00
 */
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    public double cost() {
        return .99;
    }
}
