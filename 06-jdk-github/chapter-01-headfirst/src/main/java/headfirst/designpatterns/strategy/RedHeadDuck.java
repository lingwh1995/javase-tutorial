package headfirst.designpatterns.strategy;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2023/12/7 08:25
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Red Headed duck");
    }
}
