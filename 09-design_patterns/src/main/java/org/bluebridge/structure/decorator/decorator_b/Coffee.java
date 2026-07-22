package org.bluebridge.structure.decorator.decorator_b;

/**
 * 所有咖啡公共特性的抽取
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Coffee extends Drink {

    @Override
    public double cost() {
        return super.getPrice();
    }
}
