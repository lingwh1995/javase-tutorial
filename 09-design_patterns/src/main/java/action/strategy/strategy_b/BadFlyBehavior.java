package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 糟糕的飞翔行为
 * @date 2026/7/9 00:00
 */
public class BadFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("飞翔技能糟糕......");
    }
}
