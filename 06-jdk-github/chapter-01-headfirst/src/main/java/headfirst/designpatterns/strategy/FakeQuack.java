package headfirst.designpatterns.strategy;

/**
 * 假叫
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FakeQuack implements QuackBehavior {

    public void quack() {
        System.out.println("Qwak");
    }
}
