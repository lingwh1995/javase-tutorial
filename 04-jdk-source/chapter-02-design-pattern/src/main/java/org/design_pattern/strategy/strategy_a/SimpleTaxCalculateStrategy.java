package org.design_pattern.strategy.strategy_a;

/**
 * 简单税务计算策略
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SimpleTaxCalculateStrategy implements TaxCalculatorStrategy {

    @Override
    public double calculate(double salary, double bonus) {
        return salary * 0.5 + bonus * 0.5;
    }
}
