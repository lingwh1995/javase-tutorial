package headfirst.designpatterns.combining.composite;

/**
 * 鸭子叫声
 *
 * @author lingwh
 * @date 2023/12/7 20:47
 */
public class DuckCall implements Quackable {

    @Override
    public void quack() {
        System.out.println("Kwak");
    }

    @Override
    public String toString() {
        return "Duck Call";
    }
}
