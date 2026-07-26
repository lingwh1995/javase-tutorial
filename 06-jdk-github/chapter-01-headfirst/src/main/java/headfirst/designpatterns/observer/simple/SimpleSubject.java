package headfirst.designpatterns.observer.simple;

import java.util.*;

/**
 * 简单主题
 *
 * @author lingwh
 * @date 2023/12/7 19:33
 */
public class SimpleSubject implements Subject {

    private ArrayList<Observer> observers;
    private int value = 0;

    public SimpleSubject() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }
}
