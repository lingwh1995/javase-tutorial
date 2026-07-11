package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * @author lingwh
 * @desc 调料装饰器
 * @date 2026/7/9 00:00
 */
public abstract class CondimentDecorator extends Beverage {
    public Beverage beverage;

    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
