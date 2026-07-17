package action.strategy.strategy_a;

/**
 * 减法命令
 *
 * @author lingwh
 * @date 2026/7/13 16:19
 */
public class Subtraction implements ICalucatorStrategy {

    @Override
    public Integer calucate(int a, int b) {
        return a - b;
    }
}
