package org.bluebridge.action.strategy.strategy_b;

/**
 * 优秀的游泳行为
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class GoodSwimBehavior implements SwimBehavior {

    @Override
    public void swim() {
        System.out.println("游泳技能高超.....");
    }
}
