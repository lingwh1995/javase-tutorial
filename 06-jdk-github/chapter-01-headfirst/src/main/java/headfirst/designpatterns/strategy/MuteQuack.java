package headfirst.designpatterns.strategy;

/**
 * 不叫
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MuteQuack implements QuackBehavior {

    public void quack() {
        System.out.println("<< Silence >>");
    }
}
