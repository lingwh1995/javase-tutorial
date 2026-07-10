package create.abstractfactory.abstractfactory_i;

/**
 * @author lingwh
 * @desc AMD品牌CPU
 * @date 2019/8/7 15:00
 */
public class AMDCPU implements CPUApi {

    /**
     * 针脚数
     */
    private int pins = 0;

    public AMDCPU(int pins) {
        this.pins = pins;
    }

    /**
     * CPU具有运算功能
     */
    @Override
    public void calculate() {
        System.out.println("AMD牌的CPU，针脚个数有:" + pins);
    }
}
