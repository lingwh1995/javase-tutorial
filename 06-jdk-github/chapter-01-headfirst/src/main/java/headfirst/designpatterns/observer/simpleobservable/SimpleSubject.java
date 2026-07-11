package headfirst.designpatterns.observer.simpleobservable;

import java.util.Observable;

/**
 * @author lingwh
 * @desc 简单主题
 * @date 2026/7/9 00:00
 */
public class SimpleSubject extends Observable {
    private int value = 0;

    public SimpleSubject() {}

    public void setValue(int value) {
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public int getValue() {
        return this.value;
    }
}
