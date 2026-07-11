package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 用翅膀飞行
 * @date 2026/7/9 00:00
 */
public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying!!");
    }
}
