package org.bluebridge.action.strategy.strategy_b;

/**
 * 优秀的喝水行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class GoodDrinkBehavior implements DrinkBehavior {

    @Override
    public void drink() {
        System.out.println("喝水技能高超......");
    }
}
