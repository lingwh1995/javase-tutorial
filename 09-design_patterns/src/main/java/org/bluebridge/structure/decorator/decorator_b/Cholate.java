package org.bluebridge.structure.decorator.decorator_b;

/**
 * 具体的Decorator
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Cholate extends Decorator {

    public Cholate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(8.5);
    }
}
