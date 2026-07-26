package headfirst.designpatterns.decorator.starbuzzWithSizes;

/**
 * 调料装饰器
 *
 * @author lingwh
 * @date 2023/12/7 21:39
 */
public abstract class CondimentDecorator extends Beverage {

    public Beverage beverage;

    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
