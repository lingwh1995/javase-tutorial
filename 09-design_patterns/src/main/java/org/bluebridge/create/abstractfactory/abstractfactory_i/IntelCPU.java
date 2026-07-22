package org.bluebridge.create.abstractfactory.abstractfactory_i;

/**
 * Intel牌CPU实现
 *
 * @author lingwh
 * @date 2019/8/7 14:57
 */
public class IntelCPU implements CPUApi {

    /**
     * 针脚数
     */
    private int pins = 0;

    public IntelCPU(int pins) {
        this.pins = pins;
    }

    /**
     * CPU具有运算功能
     */
    @Override
    public void calculate() {
        System.out.println("Intel牌CPU，针脚个数有:" + pins);
    }
}
