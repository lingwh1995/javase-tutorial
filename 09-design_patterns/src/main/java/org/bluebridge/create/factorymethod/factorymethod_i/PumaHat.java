package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 帽子
 *
 * @author lingwh
 * @date 2026/7/22 11:23
 */
public class PumaHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Puma Hat produce ok...");
    }
}
