package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 模型鸭
 * @date 2026/7/9 00:00
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
