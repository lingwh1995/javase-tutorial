package create.factorymethod.factorymethod_i;

/**
 * Adidas服装生产工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
