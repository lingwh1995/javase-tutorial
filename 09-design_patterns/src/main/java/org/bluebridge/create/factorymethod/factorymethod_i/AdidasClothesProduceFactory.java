package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Adidas 服装生产工厂
 *
 * @author lingwh
 * @date 2026/7/22 17:34
 */
public class AdidasClothesProduceFactory implements IClothesProduceFactory {

    @Override
    public IHat produceHat() {
        return new AdidasHat();
    }

    @Override
    public IJacket produceJacket() {
        return new AdidasJacket();
    }

    @Override
    public ITrousers produceTrousers() {
        return new AdidasTrousers();
    }

    @Override
    public IShoes produceShoes() {
        return new AdidasShoes();
    }
}
