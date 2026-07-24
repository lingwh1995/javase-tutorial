package org.design_pattern.section_02_strategy.case_01;

/**
 * 税务计算器
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public class TaxCalculator {

    private final double salary;
    private final double bonus;
    private TaxCalculatorStrategy taxCalculatorStrategy;

    public TaxCalculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calcTax() {
        return taxCalculatorStrategy.calculate(salary, bonus);
    }

    protected double calculate() {
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setTaxCalculatorStrategy(TaxCalculatorStrategy taxCalculatorStrategy) {
        this.taxCalculatorStrategy = taxCalculatorStrategy;
    }
}
