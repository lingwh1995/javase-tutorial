package headfirst.designpatterns.decorator.starbuzz;

/**
 * 调料装饰器
 *
 * @author lingwh
 * @date 2023/12/7 10:48
 */
public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDescription();
}
