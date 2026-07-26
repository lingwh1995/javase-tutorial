package headfirst.designpatterns.ducks;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 11:55
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
