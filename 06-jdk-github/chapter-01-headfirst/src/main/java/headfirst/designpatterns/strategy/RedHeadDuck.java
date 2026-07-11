package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 红头鸭
 * @date 2026/7/9 00:00
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
