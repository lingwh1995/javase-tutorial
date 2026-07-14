package headfirst.designpatterns.strategy;

/**
 * 用翅膀飞行
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FlyWithWings implements FlyBehavior {

    public void fly() {
        System.out.println("I'm flying!!");
    }
}
