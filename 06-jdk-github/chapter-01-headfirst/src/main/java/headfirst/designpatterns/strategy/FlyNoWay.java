package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 不会飞
 * @date 2026/7/9 00:00
 */
public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("I can't fly");
    }
}
