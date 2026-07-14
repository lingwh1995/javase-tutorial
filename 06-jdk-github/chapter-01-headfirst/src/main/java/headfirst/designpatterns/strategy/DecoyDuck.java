package headfirst.designpatterns.strategy;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DecoyDuck extends Duck {

    public DecoyDuck() {
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new MuteQuack());
    }

    public void display() {
        System.out.println("I'm a duck Decoy");
    }
}
