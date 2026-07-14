package headfirst.designpatterns.combining.factory;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DecoyDuck implements Quackable {

    public void quack() {
        System.out.println("<< Silence >>");
    }

    public String toString() {
        return "Decoy Duck";
    }
}
