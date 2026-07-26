package org.bluebridge.create.factorymethod.factorymethod_a;

/**
 * 调用者
 *
 * @author lingwh
 * @date 2026/7/22 09:15
 */
public class Client {

    public static void main(String[] args) {
        CarFactory audiFactory = new AudiFactory();
        audiFactory.run();

        CarFactory bydFactory = new BydFactory();
        bydFactory.run();
    }
}
