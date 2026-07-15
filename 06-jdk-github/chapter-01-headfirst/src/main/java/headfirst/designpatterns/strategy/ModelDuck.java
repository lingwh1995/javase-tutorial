package headfirst.designpatterns.strategy;

/**
 * 模型鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a model duck");
    }
}
