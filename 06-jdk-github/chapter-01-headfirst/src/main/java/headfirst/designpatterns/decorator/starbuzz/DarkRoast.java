package headfirst.designpatterns.decorator.starbuzz;

/**
 * 深焙咖啡
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    public double cost() {
        return .99;
    }
}
