package structure.decorator.decorator_b;

/**
 * @author lingwh
 * @desc 装饰器
 * @date 2026/7/9 00:00
 */
public class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + ":" +super.getPrice() + "&&" + drink.getDesc() +":" + drink.getPrice();
    }
}
