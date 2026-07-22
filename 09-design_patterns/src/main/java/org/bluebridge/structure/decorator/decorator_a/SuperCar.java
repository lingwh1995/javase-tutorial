package org.bluebridge.structure.decorator.decorator_a;

/**
 * 装饰器
 *
 * @author lingwh
 * @date 2019/3/23 19:02
 */
public class SuperCar implements ICar {

    private ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.run();
    }
}
