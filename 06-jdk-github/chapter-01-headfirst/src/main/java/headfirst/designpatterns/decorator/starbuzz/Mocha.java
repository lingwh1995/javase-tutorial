package headfirst.designpatterns.decorator.starbuzz;

/**
 * 摩卡调料
 *
 * @author lingwh
 * @date 2023/12/7 16:02
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
        return .20 + beverage.cost();
    }
}
