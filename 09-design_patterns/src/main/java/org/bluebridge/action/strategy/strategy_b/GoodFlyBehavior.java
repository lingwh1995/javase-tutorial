package org.bluebridge.action.strategy.strategy_b;

/**
 * 优秀的飞翔行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class GoodFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("飞翔技能高超......");
    }
}
