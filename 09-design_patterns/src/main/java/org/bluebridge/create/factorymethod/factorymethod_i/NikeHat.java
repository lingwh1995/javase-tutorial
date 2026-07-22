package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike帽子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeHat implements IHat {

    @Override
    public void produce() {
        System.out.println("Nike Hat produce ok...");
    }
}
