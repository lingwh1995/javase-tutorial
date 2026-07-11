package headfirst.designpatterns.ducks;

/**
 * @author lingwh
 * @desc 绿头鸭
 * @date 2026/7/9 00:00
 */
public class MallardDuck implements Duck {
    public void quack() {
        System.out.println("Quack");
    }

    public void fly() {
        System.out.println("I'm flying");
    }
}
