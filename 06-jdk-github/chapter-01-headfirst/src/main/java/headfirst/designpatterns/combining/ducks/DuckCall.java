package headfirst.designpatterns.combining.ducks;

/**
 * @author lingwh
 * @desc 鸭子鸣叫器
 * @date 2026/7/9 00:00
 */
public class DuckCall implements Quackable {
    public void quack() {
        System.out.println("Kwak");
    }
}
