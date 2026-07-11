package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike服装生产工厂
 * @date 2026/7/9 00:00
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
