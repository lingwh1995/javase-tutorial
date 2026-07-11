package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 糟糕的游泳行为
 * @date 2026/7/9 00:00
 */
public class BadSwimBehavior implements SwimBehavior {

    @Override
    public void swim() {
        System.out.println("游泳技能高超.....");
    }
}
