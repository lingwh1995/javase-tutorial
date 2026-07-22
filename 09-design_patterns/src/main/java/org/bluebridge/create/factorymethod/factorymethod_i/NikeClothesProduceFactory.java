package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Nike服装生产工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
