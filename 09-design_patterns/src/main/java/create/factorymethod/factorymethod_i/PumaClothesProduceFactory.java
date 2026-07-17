package create.factorymethod.factorymethod_i;

/**
 * Puma服装生产工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
