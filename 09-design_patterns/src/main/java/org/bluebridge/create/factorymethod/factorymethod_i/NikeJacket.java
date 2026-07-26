package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 夹克
 *
 * @author lingwh
 * @date 2026/7/22 11:47
 */
public class NikeJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Nike Jacket produce ok...");
    }
}
