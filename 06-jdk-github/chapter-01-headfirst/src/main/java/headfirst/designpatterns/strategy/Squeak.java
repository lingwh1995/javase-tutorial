package headfirst.designpatterns.strategy;

/**
 * 吱吱叫
 *
 * @author lingwh
 * @date 2023/12/7 20:20
 */
public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
