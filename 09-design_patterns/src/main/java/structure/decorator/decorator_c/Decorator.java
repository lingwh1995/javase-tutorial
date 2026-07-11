package structure.decorator.decorator_c;

/**
 * @author lingwh
 * @desc 装饰器
 * @date 2026/7/9 00:00
 */
public abstract class Decorator implements Component {
    protected Component component;

    public abstract void operation();
}
