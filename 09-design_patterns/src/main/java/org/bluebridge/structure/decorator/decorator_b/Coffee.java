package org.bluebridge.structure.decorator.decorator_b;

/**
 * 所有咖啡公共特性的抽取
 *
 * @author lingwh
 * @date 2026/7/22 10:23
 */
public class Coffee extends Drink {

    @Override
    public double cost() {
        return super.getPrice();
    }
}
