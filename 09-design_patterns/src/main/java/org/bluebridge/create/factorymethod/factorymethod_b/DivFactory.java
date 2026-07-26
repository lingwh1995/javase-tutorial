package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 除法工厂
 *
 * @author lingwh
 * @date 2026/7/22 15:18
 */
public class DivFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("除法操作工厂...");
        return new Div();
    }
}
