package create.factorymethod.factorymethod_i;

/**
 * Nike服装
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NikeClothes extends Clothes {

    public NikeClothes(IClothesProduceFactory nikeClothesProduceFactory) {
        hat = nikeClothesProduceFactory.produceHat();
        jacket = nikeClothesProduceFactory.produceJacket();
        trousers = nikeClothesProduceFactory.produceTrousers();
        shoes = nikeClothesProduceFactory.produceShoes();
    }
}
