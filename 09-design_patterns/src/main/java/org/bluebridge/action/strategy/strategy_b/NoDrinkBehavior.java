package org.bluebridge.action.strategy.strategy_b;

/**
 * 不会喝水行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class NoDrinkBehavior implements DrinkBehavior {

    @Override
    public void drink() {
        System.out.println("压根就不会喝水......");
    }
}
