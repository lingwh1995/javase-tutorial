package headfirst.designpatterns.decorator.pizza;

/**
 * 配料装饰器
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public abstract class ToppingDecorator extends Pizza {

    Pizza pizza;

    public abstract String getDescription();
}
