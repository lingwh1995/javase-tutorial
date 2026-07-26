package org.bluebridge.create.simplefactory.simplefactory_c;

/**
 * 乘法运算
 *
 * @author lingwh
 * @date 2026/7/22 11:36
 */
public class Mul implements Operation {

    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 * param2;
    }
}
