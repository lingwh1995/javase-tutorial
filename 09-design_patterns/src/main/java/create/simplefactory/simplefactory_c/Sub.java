package create.simplefactory.simplefactory_c;

/**
 * @author lingwh
 * @desc 减法运算
 * @date 2026/7/9 00:00
 */
public class Sub implements Operation {
    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 - param2;
    }
}
