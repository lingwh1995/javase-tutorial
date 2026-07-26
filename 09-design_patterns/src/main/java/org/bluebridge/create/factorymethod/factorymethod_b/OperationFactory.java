package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 运算工厂
 *
 * @author lingwh
 * @date 2026/7/22 16:31
 */
public abstract class OperationFactory {

    public Double opertion(Double a, Double b) {
        Operation operation = createOperation();
        return operation.opertion(a, b);
    }

    protected abstract Operation createOperation();
}
