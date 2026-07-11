package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 所有咖啡公共特性的抽取
 * @date 2026/7/9 00:00
 */
public class Coffee extends Drink {
    @Override
    public double cost() {
        return super.getPrice();
    }
}
