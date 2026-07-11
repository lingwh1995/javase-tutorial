package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma服装
 * @date 2026/7/9 00:00
 */
public class PumaClothes extends Clothes {

    public PumaClothes(IClothesProduceFactory pumaClothesProduceFactory) {
        hat = pumaClothesProduceFactory.produceHat();
        jacket = pumaClothesProduceFactory.produceJacket();
        trousers = pumaClothesProduceFactory.produceTrousers();
        shoes = pumaClothesProduceFactory.produceShoes();
    }
}
