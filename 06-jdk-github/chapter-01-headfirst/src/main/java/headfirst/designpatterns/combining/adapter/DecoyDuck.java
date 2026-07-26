package headfirst.designpatterns.combining.adapter;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 16:08
 */
public class DecoyDuck implements Quackable {

    public void quack() {
        System.out.println("<< Silence >>");
    }
}
