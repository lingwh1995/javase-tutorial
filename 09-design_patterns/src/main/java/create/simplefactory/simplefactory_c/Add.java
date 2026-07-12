package create.simplefactory.simplefactory_c;

/**
 * 加法运算
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Add implements Operation {

    @Override
    public Double getReslt(Double param1, Double param2) {
        return param1 + param2;
    }
}
