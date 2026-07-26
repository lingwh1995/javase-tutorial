package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 减法工厂
 *
 * @author lingwh
 * @date 2026/7/22 13:42
 */
public class SubFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("减法操作工厂...");
        return new Sub();
    }
}
