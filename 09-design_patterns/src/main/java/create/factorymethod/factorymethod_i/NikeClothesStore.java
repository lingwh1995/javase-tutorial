package create.factorymethod.factorymethod_i;

/**
 * Nike服装店
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory nikeClothesProduceFactory = new NikeClothesProduceFactory();
        return new NikeClothes(nikeClothesProduceFactory);
    }
}
