package action.strategy.strategy_b;

/**
 * 不会飞翔行为
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NoFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("压根就不会飞翔......");
    }
}
