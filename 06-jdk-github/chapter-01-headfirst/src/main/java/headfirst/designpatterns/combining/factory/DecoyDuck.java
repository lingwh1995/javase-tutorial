package headfirst.designpatterns.combining.factory;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 14:05
 */
public class DecoyDuck implements Quackable {

    public void quack() {
        System.out.println("<< Silence >>");
    }

    public String toString() {
        return "Decoy Duck";
    }
}
