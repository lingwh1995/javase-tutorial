package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 鞋子
 *
 * @author lingwh
 * @date 2026/7/22 16:43
 */
public class AdidasShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Adidas Shoes produce ok...");
    }
}
