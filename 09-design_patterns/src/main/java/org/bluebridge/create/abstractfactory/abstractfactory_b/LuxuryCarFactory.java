package org.bluebridge.create.abstractfactory.abstractfactory_b;

/**
 * 豪华汽车工厂
 *
 * @author lingwh
 * @date 2026/7/22 16:48
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
