package headfirst.designpatterns.decorator.starbuzz;

/**
 * @author lingwh
 * @desc 调料装饰器
 * @date 2026/7/9 00:00
 */
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    public abstract String getDescription();
}
