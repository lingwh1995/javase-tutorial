package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 服装店
 *
 * @author lingwh
 * @date 2026/7/22 18:12
 */
public class AdidasClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory adidasClothesProduceFactory = new AdidasClothesProduceFactory();
        return new AdidasClothes(adidasClothesProduceFactory);
    }
}
