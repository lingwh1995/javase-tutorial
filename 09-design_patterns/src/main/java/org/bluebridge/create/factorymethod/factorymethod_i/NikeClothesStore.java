package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 服装店
 *
 * @author lingwh
 * @date 2026/7/22 17:45
 */
public class NikeClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory nikeClothesProduceFactory = new NikeClothesProduceFactory();
        return new NikeClothes(nikeClothesProduceFactory);
    }
}
