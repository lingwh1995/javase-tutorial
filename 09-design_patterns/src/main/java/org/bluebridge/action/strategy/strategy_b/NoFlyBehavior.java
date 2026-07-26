package org.bluebridge.action.strategy.strategy_b;

/**
 * 不会飞翔行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class NoFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("压根就不会飞翔......");
    }
}
