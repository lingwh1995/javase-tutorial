package headfirst.designpatterns.decorator.starbuzz;

/**
 * 调料装饰器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDescription();
}
