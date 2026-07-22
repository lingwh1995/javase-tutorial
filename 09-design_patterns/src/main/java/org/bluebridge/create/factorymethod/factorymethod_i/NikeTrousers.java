package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike裤子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Nike Trousers produce ok...");
    }
}
