package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 不会喝水行为
 * @date 2026/7/9 00:00
 */
public class NoDrinkBehavior implements DrinkBehavior {
    @Override
    public void drink() {
        System.out.println("压根就不会喝水......");
    }
}
