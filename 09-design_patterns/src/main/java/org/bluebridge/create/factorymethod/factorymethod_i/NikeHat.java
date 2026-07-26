package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 帽子
 *
 * @author lingwh
 * @date 2026/7/22 10:33
 */
public class NikeHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Nike Hat produce ok...");
    }
}
