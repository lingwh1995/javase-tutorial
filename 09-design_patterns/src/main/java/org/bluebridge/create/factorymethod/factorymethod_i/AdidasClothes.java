package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 服装
 *
 * @author lingwh
 * @date 2026/7/22 10:51
 */
public class AdidasClothes extends Clothes {

    public AdidasClothes(IClothesProduceFactory adidasClothesProduceFactory) {
        hat = adidasClothesProduceFactory.produceHat();
        jacket = adidasClothesProduceFactory.produceJacket();
        trousers = adidasClothesProduceFactory.produceTrousers();
        shoes = adidasClothesProduceFactory.produceShoes();
    }
}
