package org.bluebridge.action.strategy.strategy_b;

/**
 * 糟糕的喝水行为
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BadDrinkBehavior implements DrinkBehavior {

    @Override
    public void drink() {
        System.out.println("喝水技能糟糕.....");
    }
}
