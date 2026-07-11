package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike服装
 * @date 2026/7/9 00:00
 */
public class NikeClothes extends Clothes {

    public NikeClothes(IClothesProduceFactory nikeClothesProduceFactory) {
        hat = nikeClothesProduceFactory.produceHat();
        jacket = nikeClothesProduceFactory.produceJacket();
        trousers = nikeClothesProduceFactory.produceTrousers();
        shoes = nikeClothesProduceFactory.produceShoes();
    }
}
