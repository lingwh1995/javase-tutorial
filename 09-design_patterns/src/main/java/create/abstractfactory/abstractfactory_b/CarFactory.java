package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 汽车工厂接口
 * @date 2019/3/11 00:00
 */
public interface CarFactory {
    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}
