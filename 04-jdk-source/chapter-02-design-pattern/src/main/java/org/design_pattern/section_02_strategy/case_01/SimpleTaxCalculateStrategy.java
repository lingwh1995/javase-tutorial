package org.design_pattern.section_02_strategy.case_01;

/**
 * 简单税务计算策略
 *
 * @author lingwh
 * @date 2023/12/7 15:33
 */
public class SimpleTaxCalculateStrategy implements TaxCalculatorStrategy {

    @Override
    public double calculate(double salary, double bonus) {
        return salary * 0.5 + bonus * 0.5;
    }
}
