package headfirst.designpatterns.strategy;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 09:02
 */
public class MallardDuck extends Duck {

    public MallardDuck() {

        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
