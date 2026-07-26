package headfirst.designpatterns.combining.composite;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 12:43
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
