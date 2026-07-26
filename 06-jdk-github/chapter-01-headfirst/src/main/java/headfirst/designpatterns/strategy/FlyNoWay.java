package headfirst.designpatterns.strategy;

/**
 * 不会飞
 *
 * @author lingwh
 * @date 2023/12/7 19:43
 */
public class FlyNoWay implements FlyBehavior {

    public void fly() {
        System.out.println("I can't fly");
    }
}
