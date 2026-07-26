package headfirst.designpatterns.combining.composite;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2023/12/7 15:11
 */
public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public String toString() {
        return "Redhead Duck";
    }
}
