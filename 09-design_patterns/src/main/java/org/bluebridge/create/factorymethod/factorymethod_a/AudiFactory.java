package org.bluebridge.create.factorymethod.factorymethod_a;

/**
 * 奥迪工厂
 *
 * @author lingwh
 * @date 2019/3/11 19:02
 */
public class AudiFactory extends CarFactory {

    @Override
    protected Car createCar() {
        return new Audi();
    }
}
