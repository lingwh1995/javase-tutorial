package headfirst.designpatterns.combining.adapter;

/**
 * @author lingwh
 * @desc 绿头鸭
 * @date 2026/7/9 00:00
 */
public class MallardDuck implements Quackable {
    public void quack() {
        System.out.println("Quack");
    }
}
