package headfirst.designpatterns.decorator.pizza;

/**
 * 配料装饰器
 *
 * @author lingwh
 * @date 2023/12/7 08:04
 */
public abstract class ToppingDecorator extends Pizza {

    Pizza pizza;

    @Override
    public abstract String getDescription();
}
