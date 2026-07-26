package headfirst.designpatterns.combining.decorator;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2023/12/7 10:44
 */
public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
