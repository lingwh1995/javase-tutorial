package headfirst.designpatterns.strategy;

/**
 * 嘎嘎叫
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Quack implements QuackBehavior {

    public void quack() {
        System.out.println("Quack");
    }
}
