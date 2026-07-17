package create.simplefactory.simplefactory_c;

/**
 * 减法运算
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class Sub implements Operation {

    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 - param2;
    }
}
