package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas服装
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasClothes extends Clothes {

    public AdidasClothes(IClothesProduceFactory adidasClothesProduceFactory) {
        hat = adidasClothesProduceFactory.produceHat();
        jacket = adidasClothesProduceFactory.produceJacket();
        trousers = adidasClothesProduceFactory.produceTrousers();
        shoes = adidasClothesProduceFactory.produceShoes();
    }
}
