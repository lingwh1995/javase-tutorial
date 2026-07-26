package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 加法运算
 *
 * @author lingwh
 * @date 2026/7/22 08:25
 */
public class Add implements Operation {

    @Override
    public Double opertion(Double a, Double b) {
        return a + b;
    }
}
