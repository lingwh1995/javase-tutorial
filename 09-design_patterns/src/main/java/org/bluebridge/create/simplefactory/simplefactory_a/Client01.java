package org.bluebridge.create.simplefactory.simplefactory_a;

/**
 * 调用者 1 - 不使用工厂模式
 *
 * @author lingwh
 * @date 2019/3/10 10:56
 */
public class Client01 {

    public static void main(String[] args) {
        Audi audi = new Audi();
        audi.run();

        Byd byd = new Byd();
        byd.run();
    }
}
