package headfirst.designpatterns.combining.adapter;

/**
 * @author lingwh
 * @desc 诱饵鸭
 * @date 2026/7/9 00:00
 */
public class DecoyDuck implements Quackable {
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
