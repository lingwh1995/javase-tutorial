package org.bluebridge.action.strategy.strategy_b;

/**
 * 不会游泳行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class NoSwimBehavior implements SwimBehavior {

    @Override
    public void swim() {
        System.out.println("压根就不会游泳......");
    }
}
