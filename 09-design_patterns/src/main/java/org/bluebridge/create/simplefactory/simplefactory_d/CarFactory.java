package org.bluebridge.create.simplefactory.simplefactory_d;

/**
 * 生产汽车的工厂
 *
 * @author lingwh
 * @date 2019/3/10 19:02
 */
public class CarFactory {

    /**
     * 使用反射增强工厂方法模式
     *
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Car createCar(Class clazz) throws IllegalAccessException, InstantiationException {
        return (Car) clazz.newInstance();
    }
}
