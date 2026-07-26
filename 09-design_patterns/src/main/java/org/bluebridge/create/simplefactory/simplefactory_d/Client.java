package org.bluebridge.create.simplefactory.simplefactory_d;

/**
 * 调用者 2 - 使用工厂模式
 *
 * @author lingwh
 * @date 2019/3/10 19:02
 */
public class Client {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Car audi = CarFactory.createCar(Audi.class);
        audi.run();
        Car byd = CarFactory.createCar(Byd.class);
        byd.run();
    }
}
