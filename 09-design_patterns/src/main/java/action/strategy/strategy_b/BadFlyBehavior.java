package action.strategy.strategy_b;

/**
 * 糟糕的飞翔行为
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BadFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("飞翔技能糟糕......");
    }
}
