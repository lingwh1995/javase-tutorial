package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma鞋子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Puma Shoes produce ok...");
    }
}
