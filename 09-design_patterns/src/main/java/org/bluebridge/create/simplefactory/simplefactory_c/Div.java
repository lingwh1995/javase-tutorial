package org.bluebridge.create.simplefactory.simplefactory_c;

/**
 * 除法运算
 *
 * @author lingwh
 * @date 2026/7/22 12:48
 */
public class Div implements Operation {

    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 / param2;
    }
}
