package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas裤子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Adidas Trousers produce ok...");
    }
}
