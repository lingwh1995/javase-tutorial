package headfirst.designpatterns.combining.observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 鸭群
 *
 * @author lingwh
 * @date 2023/12/7 09:25
 */
public class Flock implements Quackable {

    ArrayList<Quackable> ducks = new ArrayList<Quackable>();

    public void add(Quackable duck) {
        ducks.add(duck);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = ducks.iterator();
        while (iterator.hasNext()) {
            Quackable duck = (Quackable) iterator.next();
            duck.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = ducks.iterator();
        while (iterator.hasNext()) {
            Quackable duck = (Quackable) iterator.next();
            duck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
    }

    @Override
    public String toString() {
        return "Flock of Ducks";
    }
}
