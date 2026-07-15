package headfirst.designpatterns.adapter.ducks;

/**
 * 绿头鸭实现类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MallardDuck implements Duck {

    public void quack() {
        System.out.println("Quack");
    }

    public void fly() {
        System.out.println("I'm flying");
    }
}
