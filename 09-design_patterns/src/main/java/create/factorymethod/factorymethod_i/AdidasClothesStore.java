package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas服装店
 * @date 2026/7/9 00:00
 */
public class AdidasClothesStore extends ClothesStore {

    @Override
    Clothes produce() {
        IClothesProduceFactory adidasClothesProduceFactory = new AdidasClothesProduceFactory();
        return new AdidasClothes(adidasClothesProduceFactory);
    }
}
