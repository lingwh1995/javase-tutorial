package headfirst.designpatterns.combining.observer;

/**
 * @author lingwh
 * @desc 鸭子观察者
 * @date 2026/7/9 00:00
 */
public class Quackologist implements Observer {

    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");
    }

    public String toString() {
        return "Quackologist";
    }
}
