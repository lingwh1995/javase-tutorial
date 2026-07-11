package headfirst.designpatterns.combining.adapter;

/**
 * @author lingwh
 * @desc 鸭子叫声
 * @date 2026/7/9 00:00
 */
public class DuckCall implements Quackable {
    public void quack() {
        System.out.println("Kwak");
    }
}
