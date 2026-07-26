package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 夹克
 *
 * @author lingwh
 * @date 2026/7/22 12:47
 */
public class PumaJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Puma Jacket produce ok...");
    }
}
