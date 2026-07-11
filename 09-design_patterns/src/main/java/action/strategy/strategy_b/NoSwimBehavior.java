package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 不会游泳行为
 * @date 2026/7/9 00:00
 */
public class NoSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("压根就不会游泳......");
    }
}
