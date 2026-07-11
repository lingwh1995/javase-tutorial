package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 具体的Decorator
 * @date 2026/7/9 00:00
 */
public class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(8.0);
    }
}
