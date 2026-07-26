package headfirst.designpatterns.strategy;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 17:15
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
