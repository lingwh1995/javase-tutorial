package headfirst.designpatterns.combining.factory;

/**
 * 鸭子鸣叫器
 *
 * @author lingwh
 * @date 2023/12/7 15:23
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
