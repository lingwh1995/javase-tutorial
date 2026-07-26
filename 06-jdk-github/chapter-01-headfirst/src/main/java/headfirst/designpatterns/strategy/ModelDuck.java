package headfirst.designpatterns.strategy;

/**
 * 模型鸭
 *
 * @author lingwh
 * @date 2023/12/7 22:48
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
