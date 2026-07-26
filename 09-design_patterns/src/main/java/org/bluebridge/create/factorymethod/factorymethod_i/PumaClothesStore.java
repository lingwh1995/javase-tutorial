package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 服装店
 *
 * @author lingwh
 * @date 2026/7/22 16:44
 */
public class PumaClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory pumaClothesProduceFactory = new PumaClothesProduceFactory();
        return new PumaClothes(pumaClothesProduceFactory);
    }
}
