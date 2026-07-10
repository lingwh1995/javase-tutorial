package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas服装生产工厂
 * @date 2026/7/9 00:00
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
