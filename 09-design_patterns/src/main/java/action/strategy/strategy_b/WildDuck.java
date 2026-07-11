package action.strategy.strategy_b;

/**
 * @author lingwh
 * @desc 野鸭
 * @date 2026/7/9 00:00
 */
public class WildDuck extends Duck {
    public WildDuck() {
        System.out.println("我是一只野鸭......");
        // 野鸭飞翔技能高超
        super.flyBehavior = new GoodFlyBehavior();
        // 野鸭游泳技能高超
        super.swimBehavior = new GoodSwimBehavior();
        // 野鸭喝水技能高超
        super.drinkBehavior = new GoodDrinkBehavior();
    }
}
