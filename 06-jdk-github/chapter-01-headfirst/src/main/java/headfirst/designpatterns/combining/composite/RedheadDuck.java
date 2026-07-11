package headfirst.designpatterns.combining.composite;

/**
 * @author lingwh
 * @desc 红头鸭
 * @date 2026/7/9 00:00
 */
public class RedheadDuck implements Quackable {
    public void quack() {
        System.out.println("Quack");
    }

    public String toString() {
        return "Redhead Duck";
    }
}
