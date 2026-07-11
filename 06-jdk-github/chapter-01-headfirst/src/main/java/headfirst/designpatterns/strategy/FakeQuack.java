package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 假叫
 * @date 2026/7/9 00:00
 */
public class FakeQuack implements QuackBehavior {
    public void quack() {
        System.out.println("Qwak");
    }
}
