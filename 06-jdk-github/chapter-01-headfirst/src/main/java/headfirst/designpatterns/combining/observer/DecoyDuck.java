package headfirst.designpatterns.combining.observer;

/**
 * 诱饵鸭
 *
 * @author lingwh
 * @date 2023/12/7 19:28
 */
public class DecoyDuck implements Quackable {

    Observable observable;

    public DecoyDuck() {
        observable = new Observable(this);
    }

    public void quack() {
        System.out.println("<< Silence >>");
        notifyObservers();
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }

    public String toString() {
        return "Decoy Duck";
    }
}
