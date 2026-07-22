package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 乘法工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MulFactory extends OperationFactory {

    @Override
    protected Operation createOperation() {
        System.out.println("乘法工厂类...");
        return new Mul();
    }
}
