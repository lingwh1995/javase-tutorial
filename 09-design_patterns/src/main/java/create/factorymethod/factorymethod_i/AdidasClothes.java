package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas服装
 * @date 2026/7/9 00:00
 */
public class AdidasClothes extends Clothes {

    public AdidasClothes(IClothesProduceFactory adidasClothesProduceFactory) {
        hat = adidasClothesProduceFactory.produceHat();
        jacket = adidasClothesProduceFactory.produceJacket();
        trousers = adidasClothesProduceFactory.produceTrousers();
        shoes = adidasClothesProduceFactory.produceShoes();
    }
}
