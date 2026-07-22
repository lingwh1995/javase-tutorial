package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike夹克
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Nike Jacket produce ok...");
    }
}
