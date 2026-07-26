package headfirst.designpatterns.combining.ducks;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 10:19
 */
public class DecoyDuck implements Quackable {

    public void quack() {
        System.out.println("<< Silence >>");
    }
}
