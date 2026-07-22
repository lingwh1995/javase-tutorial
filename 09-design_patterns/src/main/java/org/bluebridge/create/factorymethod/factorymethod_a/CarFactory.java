package org.bluebridge.create.factorymethod.factorymethod_a;

/**
 * 工厂的接口
 *
 * @author lingwh
 * @date 2019/3/11 19:02
 */
public abstract class CarFactory {

    public void run() {
        Car car = createCar();
        car.run();
    }

    /**
     * 工厂方法
     *
     * @return
     */
    protected abstract Car createCar();
}
