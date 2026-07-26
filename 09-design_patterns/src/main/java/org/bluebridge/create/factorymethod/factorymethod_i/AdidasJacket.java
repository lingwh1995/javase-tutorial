package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 夹克
 *
 * @author lingwh
 * @date 2026/7/22 14:07
 */
public class AdidasJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Adidas Jacket produce ok...");
    }
}
