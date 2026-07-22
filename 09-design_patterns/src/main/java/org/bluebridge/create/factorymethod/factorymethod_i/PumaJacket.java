package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma夹克
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Puma Jacket produce ok...");
    }
}
