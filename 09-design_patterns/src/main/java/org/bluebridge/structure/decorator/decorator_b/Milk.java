package org.bluebridge.structure.decorator.decorator_b;

/**
 * 具体的 Decorator
 *
 * @author lingwh
 * @date 2026/7/22 14:35
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(8.0);
    }
}
