package org.design_pattern.strategy.strategy_a;

/**
 * @author lingwh
 * @desc 税务计算策略接口
 * @date 2026/7/9 00:00
 */
public interface TaxCalculatorStrategy {
    double calculate(double salary, double bonus);
}
