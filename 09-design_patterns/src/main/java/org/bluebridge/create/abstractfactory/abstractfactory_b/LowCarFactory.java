package org.bluebridge.create.abstractfactory.abstractfactory_b;

/**
 * 低端汽车工厂
 *
 * @author lingwh
 * @date 2026/7/22 14:33
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
