package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 豆浆调料
 * @date 2026/7/9 00:00
 */
public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    public double cost() {
        return .15 + beverage.cost();
    }
}
