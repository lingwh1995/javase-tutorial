package headfirst.designpatterns.combining.composite;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RedheadDuck implements Quackable {

    public void quack() {
        System.out.println("Quack");
    }

    public String toString() {
        return "Redhead Duck";
    }
}
