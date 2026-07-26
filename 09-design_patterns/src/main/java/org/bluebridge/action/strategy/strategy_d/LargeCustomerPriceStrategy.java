package org.bluebridge.action.strategy.strategy_d;

/**
 * 大客户：打 8 折
 *
 * @author lingwh
 * @date 2019/8/2 15:12
 */
public class LargeCustomerPriceStrategy implements PriceStragegy {

    /**
     * 针对大客户的计算策略
     *
     * @param price 具体的价格
     * @return
     */
    @Override
    public double calcPrice(Double price) {
        System.out.println("大客户......");
        return price * 0.8;
    }
}
