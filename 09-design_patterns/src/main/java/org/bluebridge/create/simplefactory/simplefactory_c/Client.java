package org.bluebridge.create.simplefactory.simplefactory_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Client {

    public static void main(String[] args) {
        Operation add = OperationFactory.createOperation("+");
        System.out.println(add.getReslt(1.0, 3.5));
    }
}
