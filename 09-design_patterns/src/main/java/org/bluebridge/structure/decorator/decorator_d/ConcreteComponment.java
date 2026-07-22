package org.bluebridge.structure.decorator.decorator_d;

/**
 * 具体组件
 *
 * @author lingwh
 * @date 2019/7/25 17:01
 */
public class ConcreteComponment extends Bread {

    @Override
    public String getDesc() {
        return "普通面包";
    }

    @Override
    public double getPrice() {
        return 2.5;
    }
}
