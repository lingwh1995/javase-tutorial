package headfirst.designpatterns.decorator.starbuzz;

/**
 * 深焙咖啡
 *
 * @author lingwh
 * @date 2023/12/7 11:25
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    public double cost() {
        return .99;
    }
}
