package org.bluebridge.structure.decorator.decorator_b;

/**
 * 装饰器
 *
 * @author lingwh
 * @date 2026/7/22 10:41
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
