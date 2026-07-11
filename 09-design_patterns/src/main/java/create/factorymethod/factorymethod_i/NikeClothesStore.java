package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike服装店
 * @date 2026/7/9 00:00
 */
public class NikeClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory nikeClothesProduceFactory = new NikeClothesProduceFactory();
        return new NikeClothes(nikeClothesProduceFactory);
    }
}
