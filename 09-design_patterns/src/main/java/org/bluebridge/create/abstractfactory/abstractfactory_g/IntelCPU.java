package org.bluebridge.create.abstractfactory.abstractfactory_g;

/**
 * Intel 的 CPU 实现
 *
 * @author lingwh
 * @date 2019/9/4 9:42
 */
public class IntelCPU implements CPUApi {

    /**
     * CPU 的针脚数目
     */
    private int pins = 0;

    /**
     * 构造方法，传入 CPU 的针脚数目
     *
     * @param pins CPU 的针脚数目
     */
    public IntelCPU(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("now in Intel CPU,pins=" + pins);
    }
}
