package org.design_pattern.section_02_strategy.case_01;

/**
 * 税务计算策略接口
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public interface TaxCalculatorStrategy {

    double calculate(double salary, double bonus);
}
