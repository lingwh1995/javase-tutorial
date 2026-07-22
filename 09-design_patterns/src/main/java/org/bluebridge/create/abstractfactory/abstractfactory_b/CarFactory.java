package org.bluebridge.create.abstractfactory.abstractfactory_b;

/**
 * 汽车工厂接口
 *
 * @author lingwh
 * @date 2019/3/11 19:02
 */
public interface CarFactory {

    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}
