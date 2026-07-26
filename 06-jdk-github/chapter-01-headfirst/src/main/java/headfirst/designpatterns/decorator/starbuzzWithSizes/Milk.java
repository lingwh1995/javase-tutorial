package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 牛奶调料
 *
 * @author lingwh
 * @date 2023/12/7 11:51
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
        return beverage.cost() + .10;
    }
}
