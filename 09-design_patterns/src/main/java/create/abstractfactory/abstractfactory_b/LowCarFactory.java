package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 低端汽车工厂
 * @date 2019/3/11 00:00
 */
public class LowCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new LowEngine();
    }

    @Override
    public Seat createSeat() {
        return new LowSeat();
    }

    @Override
    public Tyre createTyre() {
        return new LowTyre();
    }
}
