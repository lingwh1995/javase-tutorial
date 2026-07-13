package action.strategy.strategy_b;

/**
 * 糟糕的游泳行为
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BadSwimBehavior implements SwimBehavior {

    @Override
    public void swim() {
        System.out.println("游泳技能高超.....");
    }
}
