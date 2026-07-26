package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 鞋子
 *
 * @author lingwh
 * @date 2026/7/22 13:39
 */
public class PumaShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Puma Shoes produce ok...");
    }
}
