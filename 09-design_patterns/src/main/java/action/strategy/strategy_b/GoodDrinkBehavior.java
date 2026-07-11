package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 优秀的喝水行为
 * @date 2026/7/9 00:00
 */
public class GoodDrinkBehavior implements DrinkBehavior {
    @Override
    public void drink() {
        System.out.println("喝水技能高超......");
    }
}
