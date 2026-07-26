package headfirst.designpatterns.strategy;

/**
 * 火箭动力飞行
 *
 * @author lingwh
 * @date 2023/12/7 11:42
 */
public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
