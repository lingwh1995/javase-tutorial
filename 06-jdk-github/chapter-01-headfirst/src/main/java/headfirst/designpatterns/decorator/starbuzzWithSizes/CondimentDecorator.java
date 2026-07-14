package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 调料装饰器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class CondimentDecorator extends Beverage {

    public Beverage beverage;

    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
