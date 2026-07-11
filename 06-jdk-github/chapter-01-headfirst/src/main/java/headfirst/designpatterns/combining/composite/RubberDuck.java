package headfirst.designpatterns.combining.composite;

/**
 * @author lingwh
 * @desc 橡皮鸭
 * @date 2026/7/9 00:00
 */
public class RubberDuck implements Quackable {

    public void quack() {
        System.out.println("Squeak");
    }

    public String toString() {
        return "Rubber Duck";
    }
}
