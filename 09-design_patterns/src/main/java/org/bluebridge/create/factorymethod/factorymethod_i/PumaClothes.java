package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 服装
 *
 * @author lingwh
 * @date 2026/7/22 10:08
 */
public class PumaClothes extends Clothes {

    public PumaClothes(IClothesProduceFactory pumaClothesProduceFactory) {
        hat = pumaClothesProduceFactory.produceHat();
        jacket = pumaClothesProduceFactory.produceJacket();
        trousers = pumaClothesProduceFactory.produceTrousers();
        shoes = pumaClothesProduceFactory.produceShoes();
    }
}
