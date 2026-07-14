package headfirst.designpatterns.strategy;

/**
 * 不会飞
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FlyNoWay implements FlyBehavior {

    public void fly() {
        System.out.println("I can't fly");
    }
}
