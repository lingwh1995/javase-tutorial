package headfirst.designpatterns.decorator.starbuzz;

/**
 * 牛奶调料
 *
 * @author lingwh
 * @date 2023/12/7 15:49
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
