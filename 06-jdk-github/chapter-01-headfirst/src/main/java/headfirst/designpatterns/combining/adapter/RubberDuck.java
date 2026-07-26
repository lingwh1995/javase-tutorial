package headfirst.designpatterns.combining.adapter;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2023/12/7 09:03
 */
public class RubberDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
