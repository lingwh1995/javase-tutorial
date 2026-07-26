package org.bluebridge.create.factorymethod.factorymethod_b;

/**
 * 乘法运算
 *
 * @author lingwh
 * @date 2026/7/22 10:51
 */
public class Mul implements Operation {

    @Override
    public Double opertion(Double a, Double b) {
        return a * b;
    }
}
