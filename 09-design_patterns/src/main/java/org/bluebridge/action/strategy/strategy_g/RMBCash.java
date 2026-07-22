package org.bluebridge.action.strategy.strategy_g;

/**
 * 人民币支付
 *
 * @author lingwh
 * @date 2019/8/28 17:16
 */
public class RMBCash implements PaymentStrategy {

    @Override
    public void pay(PaymentContext ctx) {
        System.out.println("现在给" + ctx.getUserName() + "人民币现金支付" + ctx.getMoney() + "元");
    }
}
