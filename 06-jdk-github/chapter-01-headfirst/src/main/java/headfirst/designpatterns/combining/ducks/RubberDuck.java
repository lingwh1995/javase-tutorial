package headfirst.designpatterns.combining.ducks;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2023/12/7 16:47
 */
public class RubberDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
