package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma服装
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaClothes extends Clothes {

    public PumaClothes(IClothesProduceFactory pumaClothesProduceFactory) {
        hat = pumaClothesProduceFactory.produceHat();
        jacket = pumaClothesProduceFactory.produceJacket();
        trousers = pumaClothesProduceFactory.produceTrousers();
        shoes = pumaClothesProduceFactory.produceShoes();
    }
}
