package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 深焙咖啡
 *
 * @author lingwh
 * @date 2023/12/7 22:08
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    public double cost() {
        return .99;
    }
}
