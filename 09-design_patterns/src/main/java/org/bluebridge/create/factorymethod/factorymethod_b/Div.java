package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 除法运算
 *
 * @author lingwh
 * @date 2026/7/22 11:14
 */
public class Div implements Operation {

    @Override
    public Double opertion(Double a, Double b) {
        return a / b;
    }
}
