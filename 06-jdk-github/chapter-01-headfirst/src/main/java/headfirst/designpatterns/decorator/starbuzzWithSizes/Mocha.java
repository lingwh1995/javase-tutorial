package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 摩卡调料
 *
 * @author lingwh
 * @date 2023/12/7 12:38
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + .20;
    }
}
