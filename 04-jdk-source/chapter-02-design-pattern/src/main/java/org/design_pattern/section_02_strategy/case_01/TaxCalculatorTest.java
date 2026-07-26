package org.design_pattern.section_02_strategy.case_01;

/**
 * 税务计算器测试
 *
 * @author lingwh
 * @date 2023/12/7 09:45
 */
public class TaxCalculatorTest {

    public static void main(String[] args) {
//        TaxCalculator taxCalculator = new TaxCalculator(1000d,2000d){
//            @Override
//            protected double calcTax() {
//                return getSalary() *0.1 + getBonus() *0.1;
//            }
//        };
//        double result = taxCalculator.calculate();
//        System.out.println(result);

        TaxCalculator taxCalculator = new TaxCalculator(1000d, 2000d);
        TaxCalculatorStrategy taxCalculateStrategy = new SimpleTaxCalculateStrategy();
        taxCalculator.setTaxCalculatorStrategy(taxCalculateStrategy);
        double result = taxCalculator.calculate();
        System.out.println(result);
    }
}
