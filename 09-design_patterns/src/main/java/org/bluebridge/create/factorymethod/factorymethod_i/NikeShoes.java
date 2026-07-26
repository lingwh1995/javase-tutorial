package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 鞋子
 *
 * @author lingwh
 * @date 2026/7/22 13:21
 */
public class NikeShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Nike Shoes produce ok...");
    }
}
