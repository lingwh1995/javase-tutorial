package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 诱饵鸭
 * @date 2026/7/9 00:00
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
