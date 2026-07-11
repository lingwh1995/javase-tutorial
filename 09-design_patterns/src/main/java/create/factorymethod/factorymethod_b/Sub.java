package create.factorymethod.factorymethod_b;

/**
 * @author lingwh
 * @desc 减法运算
 * @date 2026/7/9 00:00
 */
public class Sub implements Operation {
    @Override
    public Double opertion(Double a, Double b) {
        return a - b;
    }
}
