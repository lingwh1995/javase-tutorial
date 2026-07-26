package headfirst.designpatterns.combining.adapter;

/**
 * 绿头鸭
 *
 * @author lingwh
 * @date 2023/12/7 21:36
 */
public class MallardDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
