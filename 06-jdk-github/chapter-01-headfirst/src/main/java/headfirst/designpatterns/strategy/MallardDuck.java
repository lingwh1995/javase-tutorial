package headfirst.designpatterns.strategy;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MallardDuck extends Duck {

    public MallardDuck() {

        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
