package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 裤子
 *
 * @author lingwh
 * @date 2026/7/22 14:51
 */
public class PumaTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Puma Trousers produce ok...");
    }
}
