package create.factorymethod.factorymethod_b;

/**
 * 除法运算
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Div implements Operation {

    @Override
    public Double opertion(Double a, Double b) {
        return a / b;
    }
}
