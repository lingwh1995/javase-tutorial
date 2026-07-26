package headfirst.designpatterns.decorator.starbuzz;

/**
 * 豆浆调料
 *
 * @author lingwh
 * @date 2023/12/7 17:28
 */
public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}
