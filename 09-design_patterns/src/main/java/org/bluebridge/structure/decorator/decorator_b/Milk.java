package org.bluebridge.structure.decorator.decorator_b;

/**
 * 具体的Decorator
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(8.0);
    }
}
