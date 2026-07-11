package headfirst.designpatterns.combining.decorator;

/**
 * @author lingwh
 * @desc 红头鸭
 * @date 2026/7/9 00:00
 */
public class RedheadDuck implements Quackable {
    public void quack() {
        System.out.println("Quack");
    }
}
