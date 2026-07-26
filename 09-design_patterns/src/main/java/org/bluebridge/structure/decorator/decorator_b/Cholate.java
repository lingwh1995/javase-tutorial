package org.bluebridge.structure.decorator.decorator_b;

/**
 * 具体的 Decorator
 *
 * @author lingwh
 * @date 2026/7/22 15:02
 */
public class Cholate extends Decorator {

    public Cholate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(8.5);
    }
}
