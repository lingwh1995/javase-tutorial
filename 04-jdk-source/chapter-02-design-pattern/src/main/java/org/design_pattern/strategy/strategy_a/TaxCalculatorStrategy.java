package org.design_pattern.strategy.strategy_a;

/**
 * 税务计算策略接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface TaxCalculatorStrategy {

    double calculate(double salary, double bonus);
}
