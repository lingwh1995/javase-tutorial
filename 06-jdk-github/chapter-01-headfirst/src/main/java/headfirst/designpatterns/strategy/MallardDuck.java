package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 绿头鸭
 * @date 2026/7/9 00:00
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
