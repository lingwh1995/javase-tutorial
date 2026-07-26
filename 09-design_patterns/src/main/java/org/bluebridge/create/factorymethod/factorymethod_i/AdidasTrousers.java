package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 裤子
 *
 * @author lingwh
 * @date 2026/7/22 12:25
 */
public class AdidasTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Adidas Trousers produce ok...");
    }
}
