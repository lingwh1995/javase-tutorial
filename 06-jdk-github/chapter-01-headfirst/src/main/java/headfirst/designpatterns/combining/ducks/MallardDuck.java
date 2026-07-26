package headfirst.designpatterns.combining.ducks;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 13:25
 */
public class MallardDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
