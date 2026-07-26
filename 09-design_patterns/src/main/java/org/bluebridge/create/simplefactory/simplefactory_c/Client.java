package org.bluebridge.create.simplefactory.simplefactory_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 13:55
 */
public class Client {

    public static void main(String[] args) {
        Operation add = OperationFactory.createOperation("+");
        System.out.println(add.getReslt(1.0, 3.5));
    }
}
