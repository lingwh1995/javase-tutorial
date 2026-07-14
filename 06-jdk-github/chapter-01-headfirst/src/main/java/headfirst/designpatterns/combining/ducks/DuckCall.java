package headfirst.designpatterns.combining.ducks;

/**
 * 鸭子鸣叫器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DuckCall implements Quackable {

    public void quack() {
        System.out.println("Kwak");
    }
}
