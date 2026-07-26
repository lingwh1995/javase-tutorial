package org.bluebridge.action.strategy.strategy_b;

/**
 * 糟糕的喝水行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class BadDrinkBehavior implements DrinkBehavior {

    @Override
    public void drink() {
        System.out.println("喝水技能糟糕.....");
    }
}
