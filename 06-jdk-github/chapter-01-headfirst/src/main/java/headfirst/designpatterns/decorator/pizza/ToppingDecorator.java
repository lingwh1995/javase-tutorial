package headfirst.designpatterns.decorator.pizza;

/**
 * @author lingwh
 * @desc 配料装饰器
 * @date 2026/7/9 00:00
 */
public abstract class ToppingDecorator extends Pizza {
    Pizza pizza;

    public abstract String getDescription();
}
