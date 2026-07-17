package action.strategy.strategy_b;

/**
 * 不会喝水行为
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NoDrinkBehavior implements DrinkBehavior {

    @Override
    public void drink() {
        System.out.println("压根就不会喝水......");
    }
}
