package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike 服装生产工厂
 *
 * @author lingwh
 * @date 2026/7/22 16:12
 */
public class NikeClothesProduceFactory implements IClothesProduceFactory {

    @Override
    public IHat produceHat() {
        return new NikeHat();
    }

    @Override
    public IJacket produceJacket() {
        return new NikeJacket();
    }

    @Override
    public ITrousers produceTrousers() {
        return new NikeTrousers();
    }

    @Override
    public IShoes produceShoes() {
        return new NikeShoes();
    }
}
