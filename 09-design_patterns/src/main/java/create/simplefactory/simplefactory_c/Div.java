package create.simplefactory.simplefactory_c;

/**
 * @author lingwh
 * @desc 除法运算
 * @date 2026/7/9 00:00
 */
public class Div implements Operation {
    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 / param2;
    }
}
