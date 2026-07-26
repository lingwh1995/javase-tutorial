package headfirst.designpatterns.strategy;

/**
 * 用翅膀飞行
 *
 * @author lingwh
 * @date 2023/12/7 12:19
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}
