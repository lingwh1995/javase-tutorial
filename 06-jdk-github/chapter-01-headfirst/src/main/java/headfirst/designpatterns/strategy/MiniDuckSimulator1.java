package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 迷你鸭子模拟器1
 * @date 2026/7/9 00:00
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
