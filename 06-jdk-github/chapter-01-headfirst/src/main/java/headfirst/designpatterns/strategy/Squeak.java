package headfirst.designpatterns.strategy;

/**
 * @author lingwh
 * @desc 吱吱叫
 * @date 2026/7/9 00:00
 */
public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("Squeak");
    }
}
