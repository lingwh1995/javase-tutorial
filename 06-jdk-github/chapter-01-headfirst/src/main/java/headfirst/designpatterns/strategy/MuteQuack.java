package headfirst.designpatterns.strategy;

/**
 * 不叫
 *
 * @author lingwh
 * @date 2023/12/7 18:29
 */
public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
