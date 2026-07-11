package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma服装生产工厂
 * @date 2026/7/9 00:00
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
