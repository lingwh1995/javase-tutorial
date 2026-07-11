package org.design_pattern.strategy.strategy_a;

/**
 * @author lingwh
 * @desc 简单税务计算策略
 * @date 2026/7/9 00:00
 */
public class SimpleTaxCalculateStrategy implements TaxCalculatorStrategy {

    @Override
    public double calculate(double salary, double bonus) {
        return salary * 0.5 + bonus * 0.5;
    }
}
