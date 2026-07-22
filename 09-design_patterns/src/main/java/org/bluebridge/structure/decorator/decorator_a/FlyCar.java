package org.bluebridge.structure.decorator.decorator_a;

/**
 * 具体装饰器/具体装饰角色
 *
 * @author lingwh
 * @date 2019/3/23 19:02
 */
public class FlyCar extends SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    @Override
    public void run() {
        super.run();
        fly();
    }

    /**
     * 增强的功能
     *
     * @param
     * @return void
     * @throws
     */
    private void fly() {
        System.out.println("天上飞......");
    }
}
