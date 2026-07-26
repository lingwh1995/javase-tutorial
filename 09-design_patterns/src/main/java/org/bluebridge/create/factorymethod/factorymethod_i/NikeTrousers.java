package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 裤子
 *
 * @author lingwh
 * @date 2026/7/22 14:58
 */
public class NikeTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Nike Trousers produce ok...");
    }
}
