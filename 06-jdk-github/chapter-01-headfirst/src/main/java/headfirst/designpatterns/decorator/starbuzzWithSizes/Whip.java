package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 奶泡调料
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double cost() {
        return beverage.cost() + .10;
    }
}
