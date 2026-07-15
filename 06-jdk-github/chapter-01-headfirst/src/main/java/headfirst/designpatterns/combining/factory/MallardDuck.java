package headfirst.designpatterns.combining.factory;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MallardDuck implements Quackable {

    public void quack() {
        System.out.println("Quack");
    }

    public String toString() {
        return "Mallard Duck";
    }
}
