package org.bluebridge.action.strategy.strategy_a;

/**
 * 减法命令
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Subtraction implements ICalucatorStrategy {

    @Override
    public Integer calucate(int a, int b) {
        return a - b;
    }
}
