package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 牛奶调料
 * @date 2026/7/9 00:00
 */
public class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    public double cost() {
        return .10 + beverage.cost();
    }
}
