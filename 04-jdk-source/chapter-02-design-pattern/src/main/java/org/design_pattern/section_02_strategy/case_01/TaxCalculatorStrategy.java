package org.design_pattern.section_02_strategy.case_01;

/**
 * 税务计算策略接口
 *
 * @author lingwh
 * @date 2023/12/7 13:19
 */
public interface TaxCalculatorStrategy {

    double calculate(double salary, double bonus);
}
