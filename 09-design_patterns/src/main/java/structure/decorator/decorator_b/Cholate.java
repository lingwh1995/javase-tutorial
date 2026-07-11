package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 具体的Decorator
 * @date 2026/7/9 00:00
 */
public class Cholate extends Decorator {

    public Cholate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(8.5);
    }
}
