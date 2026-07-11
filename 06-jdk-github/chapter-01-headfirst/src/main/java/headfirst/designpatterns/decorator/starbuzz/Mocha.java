package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 摩卡调料
 * @date 2026/7/9 00:00
 */
public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double cost() {
        return .20 + beverage.cost();
    }
}
