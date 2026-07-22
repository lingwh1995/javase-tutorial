package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas服装店
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory adidasClothesProduceFactory = new AdidasClothesProduceFactory();
        return new AdidasClothes(adidasClothesProduceFactory);
    }
}
