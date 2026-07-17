package headfirst.designpatterns.combining.decorator;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RubberDuck implements Quackable {

    public void quack() {
        System.out.println("Squeak");
    }

    public String toString() {
        return "Rubber Duck";
    }
}
