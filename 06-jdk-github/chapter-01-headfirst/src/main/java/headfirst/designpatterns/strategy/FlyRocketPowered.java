package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 火箭动力飞行
 * @date 2026/7/9 00:00
 */
public class FlyRocketPowered implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
