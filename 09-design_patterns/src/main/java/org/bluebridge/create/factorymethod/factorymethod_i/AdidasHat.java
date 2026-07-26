package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 帽子
 *
 * @author lingwh
 * @date 2026/7/22 11:18
 */
public class AdidasHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Adidas Hat produce ok...");
    }
}
