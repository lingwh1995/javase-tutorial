package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 奶泡调料
 *
 * @author lingwh
 * @date 2023/12/7 15:36
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
