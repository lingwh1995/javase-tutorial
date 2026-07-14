package headfirst.designpatterns.combining.adapter;

/**
 * 鸭子叫声
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DuckCall implements Quackable {

    public void quack() {
        System.out.println("Kwak");
    }
}
