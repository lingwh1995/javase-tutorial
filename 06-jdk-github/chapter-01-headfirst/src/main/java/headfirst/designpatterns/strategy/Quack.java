package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 嘎嘎叫
 * @date 2026/7/9 00:00
 */
public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}
