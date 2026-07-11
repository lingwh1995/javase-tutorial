package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma服装店
 * @date 2026/7/9 00:00
 */
public class PumaClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory pumaClothesProduceFactory = new PumaClothesProduceFactory();
        return new PumaClothes(pumaClothesProduceFactory);
    }
}
