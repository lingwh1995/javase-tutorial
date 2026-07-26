package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 加法工厂
 *
 * @author lingwh
 * @date 2026/7/22 12:27
 */
public class AddFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("加法操作工厂...");
        return new Add();
    }
}
