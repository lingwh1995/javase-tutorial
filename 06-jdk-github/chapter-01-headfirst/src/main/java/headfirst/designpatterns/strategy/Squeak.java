package headfirst.designpatterns.strategy;

/**
 * 吱吱叫
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Squeak implements QuackBehavior {

    public void quack() {
        System.out.println("Squeak");
    }
}
