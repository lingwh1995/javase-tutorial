package headfirst.designpatterns.combining.factory;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 12:38
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
