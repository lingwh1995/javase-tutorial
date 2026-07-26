package headfirst.designpatterns.strategy;

/**
 * 迷你鸭子模拟器1
 *
 * @author lingwh
 * @date 2023/12/7 11:05
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
