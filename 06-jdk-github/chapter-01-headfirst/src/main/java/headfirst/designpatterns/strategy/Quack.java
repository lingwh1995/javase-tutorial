package headfirst.designpatterns.strategy;

/**
 * 嘎嘎叫
 *
 * @author lingwh
 * @date 2023/12/7 21:34
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
