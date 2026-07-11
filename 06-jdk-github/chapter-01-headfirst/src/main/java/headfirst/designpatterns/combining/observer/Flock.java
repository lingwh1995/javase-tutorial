package headfirst.designpatterns.combining.observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author lingwh
 * @desc 鸭群
 * @date 2026/7/9 00:00
 */
public class Flock implements Quackable {
    ArrayList<Quackable> ducks = new ArrayList<Quackable>();

    public void add(Quackable duck) {
        ducks.add(duck);
    }

    public void quack() {
        Iterator<Quackable> iterator = ducks.iterator();
        while (iterator.hasNext()) {
            Quackable duck = (Quackable) iterator.next();
            duck.quack();
        }
    }

    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = ducks.iterator();
        while (iterator.hasNext()) {
            Quackable duck = (Quackable) iterator.next();
            duck.registerObserver(observer);
        }
    }

    public void notifyObservers() {}

    public String toString() {
        return "Flock of Ducks";
    }
}
