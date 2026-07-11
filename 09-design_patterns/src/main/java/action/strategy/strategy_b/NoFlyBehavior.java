package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 不会飞翔行为
 * @date 2026/7/9 00:00
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("压根就不会飞翔......");
    }
}
