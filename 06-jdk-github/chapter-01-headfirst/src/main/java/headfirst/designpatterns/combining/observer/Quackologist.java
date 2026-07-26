package headfirst.designpatterns.combining.observer;

/**
 * 鸭子观察者
 *
 * @author lingwh
 * @date 2023/12/7 18:14
 */
public class Quackologist implements Observer {

    @Override
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");
    }

    @Override
    public String toString() {
        return "Quackologist";
    }
}
