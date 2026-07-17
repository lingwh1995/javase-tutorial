package headfirst.designpatterns.combining.observer;

/**
 * 红头鸭
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RedheadDuck implements Quackable {

    Observable observable;

    public RedheadDuck() {
        observable = new Observable(this);
    }

    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }

    public String toString() {
        return "Redhead Duck";
    }
}
