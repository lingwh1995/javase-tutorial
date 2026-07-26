package org.bluebridge.action.strategy.strategy_b;

/**
 * 鸭子抽象类
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public abstract class Duck {
    /**
     * 聚合飞翔技能
     */
    protected FlyBehavior flyBehavior;

    /**
     * 聚合游泳技能
     */
    protected SwimBehavior swimBehavior;

    /**
     * 聚合喝水技能
     */
    protected DrinkBehavior drinkBehavior;

    /**
     * 设置实际的飞翔行为/重置鸭子飞翔行为/改变鸭子飞翔行为
     *
     * @param flyBehavior
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    /**
     * 设置实际的游泳行为/重置鸭子游泳行为/改变鸭子游泳行为
     *
     * @param swimBehavior
     */
    public void setSwimBehavior(SwimBehavior swimBehavior) {
        this.swimBehavior = swimBehavior;
    }

    /**
     * 设置实际的喝水行为/重置鸭子喝水行为/改变鸭子喝水行为
     *
     * @param drinkBehavior
     */
    public void setDrinkBehavior(DrinkBehavior drinkBehavior) {
        this.drinkBehavior = drinkBehavior;
    }

    public void fly() {
        if (null != flyBehavior) {
            // 鸭子对象不亲自执行飞翔相关操作，而是委托给 flyBehavior 实际引用 的对象(即 FlyBehavior 接口的具体实现类))执行
            flyBehavior.fly();
        }
    }

    public void swim() {
        if (null != swimBehavior) {
            // 鸭子对象不亲自执行游泳相关操作，而是委托给 swimBehavior 实际引用 的对象(即 SwimBehavior 接口的具体实现类)执行
            swimBehavior.swim();
        }
    }

    public void drink() {
        if (null != drinkBehavior) {
            // 鸭子对象不亲自执行喝水相关操作，而是委托给 drinkBehavior 实际引用 的对象(即 DrinkBehavior 接口的具体实现类)执行
            drinkBehavior.drink();
        }
    }
}
