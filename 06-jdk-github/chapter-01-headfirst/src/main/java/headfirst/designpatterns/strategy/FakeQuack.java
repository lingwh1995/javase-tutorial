package headfirst.designpatterns.strategy;

/**
 * 假叫
 *
 * @author lingwh
 * @date 2023/12/7 19:06
 */
public class FakeQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Qwak");
    }
}
