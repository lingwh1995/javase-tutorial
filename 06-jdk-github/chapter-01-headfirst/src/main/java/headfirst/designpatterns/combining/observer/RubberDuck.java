package headfirst.designpatterns.combining.observer;

/**
 * 橡皮鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RubberDuck implements Quackable {

    Observable observable;

    public RubberDuck() {
        observable = new Observable(this);
    }

    public void quack() {
        System.out.println("Squeak");
        notifyObservers();
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }

    public String toString() {
        return "Rubber Duck";
    }
}
