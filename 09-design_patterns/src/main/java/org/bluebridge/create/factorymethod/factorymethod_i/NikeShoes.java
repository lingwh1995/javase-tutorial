package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike鞋子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Nike Shoes produce ok...");
    }
}
