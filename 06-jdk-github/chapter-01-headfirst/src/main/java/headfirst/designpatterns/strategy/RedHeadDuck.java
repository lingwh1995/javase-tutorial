package headfirst.designpatterns.strategy;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a real Red Headed duck");
    }
}
