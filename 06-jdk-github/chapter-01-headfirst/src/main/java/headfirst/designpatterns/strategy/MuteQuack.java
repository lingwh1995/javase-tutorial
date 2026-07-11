package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 不叫
 * @date 2026/7/9 00:00
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
