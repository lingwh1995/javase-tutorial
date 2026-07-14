package headfirst.designpatterns.strategy;

/**
 * 火箭动力飞行
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FlyRocketPowered implements FlyBehavior {

    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
