package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 减法运算
 *
 * @author lingwh
 * @date 2026/7/22 09:38
 */
public class Sub implements Operation {

    @Override
    public Double opertion(Double a, Double b) {
        return a - b;
    }
}
