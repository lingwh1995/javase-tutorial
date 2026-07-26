package headfirst.designpatterns.adapter.ducks;

/**
 * 绿头鸭实现类
 *
 * @author lingwh
 * @date 2023/12/7 17:08
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
