package headfirst.designpatterns.combining.adapter;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2023/12/7 08:27
 */
public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
