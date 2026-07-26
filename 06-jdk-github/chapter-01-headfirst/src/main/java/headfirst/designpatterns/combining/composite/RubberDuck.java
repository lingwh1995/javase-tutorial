package headfirst.designpatterns.combining.composite;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2023/12/7 16:44
 */
public class RubberDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }

    @Override
    public String toString() {
        return "Rubber Duck";
    }
}
