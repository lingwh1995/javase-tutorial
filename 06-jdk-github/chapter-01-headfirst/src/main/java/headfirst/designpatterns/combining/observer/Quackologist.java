package headfirst.designpatterns.combining.observer;

/**
 * 鸭子观察者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Quackologist implements Observer {

    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");
    }

    public String toString() {
        return "Quackologist";
    }
}
