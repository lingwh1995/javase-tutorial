package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * 服装抽象类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Clothes {

    IHat hat;
    IJacket jacket;
    ITrousers trousers;
    IShoes shoes;

    void pack() {
        hat.produce();
        jacket.produce();
        trousers.produce();
        shoes.produce();
    }
}
