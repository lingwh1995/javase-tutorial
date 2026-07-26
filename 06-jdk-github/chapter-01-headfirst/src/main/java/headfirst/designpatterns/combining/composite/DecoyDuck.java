package headfirst.designpatterns.combining.composite;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 19:14
 */
public class DecoyDuck implements Quackable {

    public void quack() {
        System.out.println("<< Silence >>");
    }

    public String toString() {
        return "Decoy Duck";
    }
}
