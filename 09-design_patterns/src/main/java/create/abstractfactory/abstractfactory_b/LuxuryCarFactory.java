package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 豪华汽车工厂
 * @date 2019/3/11 00:00
 */
public class LuxuryCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new LuxuryEngine();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }
}
