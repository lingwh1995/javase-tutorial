package headfirst.designpatterns.combining.ducks;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2023/12/7 15:33
 */
public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
