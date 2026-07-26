package headfirst.designpatterns.strategy;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2023/12/7 08:00
 */
public class RubberDuck extends Duck {

    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        // quackBehavior = new Squeak();
        quackBehavior = () -> System.out.println("Squeak");
    }

    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    @Override
    public void display() {
        System.out.println("I'm a rubber duckie");
    }
}
