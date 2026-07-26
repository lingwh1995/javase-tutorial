package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * Puma 服装生产工厂
 *
 * @author lingwh
 * @date 2026/7/22 15:27
 */
public class PumaClothesProduceFactory implements IClothesProduceFactory {

    @Override
    public IHat produceHat() {
        return new PumaHat();
    }

    @Override
    public IJacket produceJacket() {
        return new PumaJacket();
    }

    @Override
    public ITrousers produceTrousers() {
        return new PumaTrousers();
    }

    @Override
    public IShoes produceShoes() {
        return new PumaShoes();
    }
}
