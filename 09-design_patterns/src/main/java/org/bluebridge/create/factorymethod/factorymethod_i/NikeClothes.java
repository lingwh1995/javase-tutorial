package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 服装
 *
 * @author lingwh
 * @date 2026/7/22 09:55
 */
public class NikeClothes extends Clothes {

    public NikeClothes(IClothesProduceFactory nikeClothesProduceFactory) {
        hat = nikeClothesProduceFactory.produceHat();
        jacket = nikeClothesProduceFactory.produceJacket();
        trousers = nikeClothesProduceFactory.produceTrousers();
        shoes = nikeClothesProduceFactory.produceShoes();
    }
}
