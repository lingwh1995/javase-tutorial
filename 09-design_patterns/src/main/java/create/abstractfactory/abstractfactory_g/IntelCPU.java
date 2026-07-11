package create.abstractfactory.abstractfactory_g;

/**
 * @author lingwh
 * @desc Intel的CPU实现
 * @date 2019/9/4 9:42
 */
public class IntelCPU implements CPUApi {

    /**
     * CPU的针脚数目
     */
    private int pins = 0;

    /**
     * 构造方法，传入CPU的针脚数目
     *
     * @param pins CPU的针脚数目
     */
    public IntelCPU(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("now in Intel CPU,pins=" + pins);
    }
}
