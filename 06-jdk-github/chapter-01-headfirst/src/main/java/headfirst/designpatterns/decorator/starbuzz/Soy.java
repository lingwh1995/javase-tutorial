package headfirst.designpatterns.decorator.starbuzz;

/**
 * 豆浆调料
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
