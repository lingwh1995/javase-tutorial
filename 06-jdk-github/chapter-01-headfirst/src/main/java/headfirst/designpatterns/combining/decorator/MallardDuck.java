package headfirst.designpatterns.combining.decorator;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 22:01
 */
public class MallardDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public String toString() {
        return "Mallard Duck";
    }
}
