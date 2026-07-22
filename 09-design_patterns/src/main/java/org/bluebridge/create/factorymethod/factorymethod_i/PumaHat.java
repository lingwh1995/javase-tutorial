package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma帽子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Puma Hat produce ok...");
    }
}
