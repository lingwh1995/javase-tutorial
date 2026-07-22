package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma服装店
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory pumaClothesProduceFactory = new PumaClothesProduceFactory();
        return new PumaClothes(pumaClothesProduceFactory);
    }
}
