package org.bluebridge.structure.decorator.decorator_a;

/**
 * 具体装饰器/具体装饰角色
 *
 * @author lingwh
 * @date 2026/7/22 08:37
 */
public class AICar extends SuperCar {

    public AICar(ICar car) {
        super(car);
    }

    @Override
    public void run() {
        super.run();
        autoRun();
    }

    /**
     * 增强的功能
     *
     * @param
     * @return void
     * @throws
     */
    private void autoRun() {
        System.out.println("自动驾驶......");
    }
}
