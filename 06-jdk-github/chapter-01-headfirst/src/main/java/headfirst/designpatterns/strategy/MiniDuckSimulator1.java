package headfirst.designpatterns.strategy;

/**
 * 迷你鸭子模拟器1
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MiniDuckSimulator1 {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
